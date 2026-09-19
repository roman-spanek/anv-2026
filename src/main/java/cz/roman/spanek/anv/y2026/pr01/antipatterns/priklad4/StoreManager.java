package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad4;

import java.util.*;

public class StoreManager {

    private final Map<String, String> users = new HashMap<>();
    private final Map<String, String> userPasswords = new HashMap<>();
    private final Map<String, Double> userBalances = new HashMap<>();
    private final Map<String, Integer> inventory = new HashMap<>();
    private final Map<String, Double> prices = new HashMap<>();
    private final List<String> orderHistory = new ArrayList<>();
    private final List<String> emailQueue = new ArrayList<>();
    private final List<String> auditLog = new ArrayList<>();
    private String currentUser;
    private boolean loggedIn = false;
    private double sessionRevenue = 0;

    public boolean registerUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            auditLog.add("Registration failed, duplicate: " + username);
            return false;
        }
        users.put(username, email);
        userPasswords.put(username, hashPassword(password));
        userBalances.put(username, 0.0);
        emailQueue.add("Welcome email to " + email);
        auditLog.add("Registered: " + username);
        return true;
    }

    private String hashPassword(String password) {
        int hash = 0;
        for (char c : password.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Integer.toString(hash);
    }

    public boolean login(String username, String password) {
        if (!users.containsKey(username)) {
            auditLog.add("Login failed, unknown user: " + username);
            return false;
        }
        if (!userPasswords.get(username).equals(hashPassword(password))) {
            auditLog.add("Login failed, bad password: " + username);
            return false;
        }
        currentUser = username;
        loggedIn = true;
        auditLog.add("Login success: " + username);
        return true;
    }

    public void logout() {
        auditLog.add("Logout: " + currentUser);
        currentUser = null;
        loggedIn = false;
    }

    public void addProduct(String name, int quantity, double price) {
        inventory.put(name, inventory.getOrDefault(name, 0) + quantity);
        prices.put(name, price);
        auditLog.add("Stock added: " + name + " x" + quantity);
    }

    public boolean placeOrder(String product, int quantity) {
        if (!loggedIn) {
            auditLog.add("Order rejected, not logged in");
            return false;
        }
        if (!inventory.containsKey(product) || inventory.get(product) < quantity) {
            auditLog.add("Order rejected, insufficient stock: " + product);
            return false;
        }

        double price = prices.get(product);
        double total = price * quantity;
        double balance = userBalances.get(currentUser);

        if (balance < total) {
            double shortfall = total - balance;
            auditLog.add(currentUser + " short by " + shortfall);
            emailQueue.add("Insufficient funds notice to " + users.get(currentUser));
            return false;
        }

        userBalances.put(currentUser, balance - total);
        inventory.put(product, inventory.get(product) - quantity);
        sessionRevenue += total;

        String orderId = "ORD-" + orderHistory.size();
        orderHistory.add(orderId + ": " + currentUser + " bought " + quantity + " " + product);
        emailQueue.add("Order confirmation to " + users.get(currentUser));
        auditLog.add("Order placed: " + orderId);

        if (inventory.get(product) < 5) {
            emailQueue.add("Low stock alert to admin@store.com for " + product);
        }

        return true;
    }

    public void depositFunds(String username, double amount) {
        userBalances.put(username, userBalances.getOrDefault(username, 0.0) + amount);
        auditLog.add("Deposit: " + username + " +" + amount);
    }

    public void flushEmailQueue() {
        for (String email : emailQueue) {
            System.out.println("[EMAIL] " + email);
        }
        emailQueue.clear();
    }

    public void printDailyReport() {
        System.out.println("=== Daily Report ===");
        System.out.println("Total orders: " + orderHistory.size());
        System.out.println("Session revenue: " + sessionRevenue);
        System.out.println("Users registered: " + users.size());
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() < 5) {
                System.out.println("Low stock: " + entry.getKey());
            }
        }
    }

    public List<String> getAuditLog() {
        return auditLog;
    }

    public static void main(String[] args) {
        StoreManager store = new StoreManager();
        store.registerUser("alice", "pw123", "alice@example.com");
        store.login("alice", "pw123");
        store.depositFunds("alice", 100.0);
        store.addProduct("widget", 10, 9.99);
        store.placeOrder("widget", 3);
        store.flushEmailQueue();
        store.printDailyReport();
    }
}