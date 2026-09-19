package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad5;

import java.util.*;
import java.io.*;

public class ShippingCalculator {

    public static void main(String[] args) throws Exception {
        ShippingCalculator calc = new ShippingCalculator();

        double cost = calc.calculateShipping("CZ", 2.5, false);
        System.out.println("Shipping cost: " + cost + " CZK");

        calc.sendConfirmationEmail("jan.novak@example.com", cost);
        calc.logTransaction("jan.novak", cost);

        calc.connectToPaymentGateway();
    }

    public double calculateShipping(String countryCode, double weightKg, boolean express) {
        double base;

        if (countryCode.equals("CZ")) {
            base = 89;
        } else if (countryCode.equals("SK")) {
            base = 129;
        } else if (countryCode.equals("DE")) {
            base = 249;
        } else {
            base = 349;
        }

        if (weightKg > 5) {
            base = base + 150;
        } else if (weightKg > 2) {
            base = base + 60;
        }

        if (express) {
            base = base * 1.75;
        }

        if (base > 999) {
            base = 999;
        }

        return base;
    }

    public void sendConfirmationEmail(String email, double amount) throws IOException {
        String smtpHost = "smtp.mycompany.local";
        int smtpPort = 2525;
        String from = "orders@mycompany-shop.cz";

        System.out.println("Connecting to " + smtpHost + ":" + smtpPort);
        System.out.println("Sending from " + from + " to " + email
                + ": Your shipping costs " + amount + " CZK");
    }

    public void logTransaction(String username, double amount) throws IOException {
        String logPath = "C:\\Users\\jnovak\\AppData\\Local\\ShopApp\\logs\\transactions.log";
        System.out.println("Would write to " + logPath + ": " + username + " " + amount);
    }

    public void connectToPaymentGateway() {
        String apiUrl = "https://api.payments-provider.com/v2/charge";
        String api = "";
        int timeoutMs = 3000;

        System.out.println("Calling " + apiUrl + " with key " + api
                + ", timeout " + timeoutMs + "ms");
    }
}