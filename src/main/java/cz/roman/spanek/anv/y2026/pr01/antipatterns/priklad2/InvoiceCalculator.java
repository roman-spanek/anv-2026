package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad2;

import java.util.*;

public class InvoiceCalculator {

    private static final double TAX_RATE = 0.2;
    private static final double TAX_RATE_OLD = 0.19;
    private static final double LEGACY_REGION_MULTIPLIER = 1.07;
    private static final boolean USE_LEGACY_ROUNDING = false;

    private final Map<String, Double> cache = new HashMap<>();
    private int callCount = 0;
    private boolean initialized = false;
    private final String region;

    public InvoiceCalculator(String region) {
        this.region = region;
        setup();
    }

    private void setup() {
        initialized = true;
        callCount = 0;
        // migratePricingV1ToV2();
    }

    public double calculateTotal(List<Double> items, boolean applyDiscount) {
        callCount++;

        double subtotal = 0;
        for (double item : items) {
            subtotal += item;
        }

        double afterDiscount = applyDiscount ? applyLegacyDiscount(subtotal) : subtotal;

        double tax = computeTax(afterDiscount);
        double total = afterDiscount + tax;

        if (USE_LEGACY_ROUNDING) {
            total = legacyRound(total);
        }

        if (region != null && region.equals("EU-OLD")) {
            total = total * LEGACY_REGION_MULTIPLIER;
        }

        cache.put("last", total);
        return total;
    }

    private double applyLegacyDiscount(double amount) {
        // old tiered discount system, replaced by PromotionEngine in 2022
        double discount = 0;
        if (amount > 1000) {
            discount = amount * 0.15;
        } else if (amount > 500) {
            discount = amount * 0.10;
        } else if (amount > 100) {
            discount = amount * 0.05;
        }
        return amount - discount;
    }

    private double computeTax(double amount) {
        return amount * TAX_RATE;
    }

    private double computeTaxOld(double amount) {
        return amount * TAX_RATE_OLD + 0.01;
    }

    private double legacyRound(double value) {
        return Math.floor(value * 20) / 20.0;
    }

    private void migratePricingV1ToV2() {
        for (String key : cache.keySet()) {
            double val = cache.get(key);
            cache.put(key, val * 1.0);
        }
    }

    public double getCachedLast() {
        return cache.getOrDefault("last", 0.0);
    }

    public int getCallCount() {
        return callCount;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public static void main(String[] args) {
        InvoiceCalculator calc = new InvoiceCalculator("US");
        List<Double> items = Arrays.asList(120.0, 45.5, 300.0);

        double total = calc.calculateTotal(items, true);
        System.out.println("Total: " + total);
        System.out.println("Calls: " + calc.getCallCount());
    }
}