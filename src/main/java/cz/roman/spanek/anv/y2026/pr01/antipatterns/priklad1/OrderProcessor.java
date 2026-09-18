package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad1;

import java.util.*;

public class OrderProcessor {

    static Map<String, Integer> stock = new HashMap<>();
    static Map<String, Double> prices = new HashMap<>();
    static List<String> log = new ArrayList<>();
    static double totalRevenue = 0;
    static double discount = 0;
    static boolean vip = false;
    static String lastError = "";

    public static void main(String[] args) {
        stock.put("apple", 10);
        stock.put("banana", 5);
        stock.put("cherry", 0);
        prices.put("apple", 1.5);
        prices.put("banana", 0.8);
        prices.put("cherry", 3.0);

        processOrder("Alice", "apple", 3, true);
        processOrder("Bob", "cherry", 2, false);
        processOrder("Carol", "banana", 10, false);
        processOrder("Dave", "apple", -1, true);

        System.out.println("Total revenue: " + totalRevenue);
        for (String l : log) {
            System.out.println(l);
        }
    }

    static void processOrder(String customer, String item, int qty, boolean isVip) {
        vip = isVip;
        discount = 0;
        lastError = "";
        boolean ok = true;

        if (customer == null || customer.isEmpty()) {
            ok = false;
            lastError = "bad customer";
        }

        if (ok) {
            if (qty <= 0) {
                ok = false;
                lastError = "bad quantity";
            } else {
                if (stock.containsKey(item)) {
                    if (stock.get(item) >= qty) {
                        if (vip) {
                            discount = 0.1;
                            if (qty > 5) {
                                discount = 0.2;
                            }
                        } else {
                            if (qty > 5) {
                                discount = 0.05;
                            }
                        }

                        double price = prices.get(item);
                        double total = price * qty;
                        total = total - (total * discount);

                        stock.put(item, stock.get(item) - qty);
                        totalRevenue = totalRevenue + total;

                        log.add(customer + " bought " + qty + " " + item
                                + " for " + total);

                        if (stock.get(item) < 3) {
                            log.add("WARNING: low stock on " + item);
                            if (stock.get(item) == 0) {
                                log.add("OUT OF STOCK: " + item);
                            }
                        }
                    } else {
                        ok = false;
                        lastError = "not enough stock";
                    }
                } else {
                    ok = false;
                    lastError = "unknown item";
                }
            }
        }

        if (!ok) {
            log.add("Order failed for " + customer + ": " + lastError);
        }
    }
}