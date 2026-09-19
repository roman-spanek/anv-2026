package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad6;

import java.util.*;

public class PhysicsSimulator {

    public static void main(String[] args) {
        PhysicsSimulator sim = new PhysicsSimulator();

        double result = sim.simulateFall(12.5, 3.2);
        System.out.println("Impact velocity: " + result + " m/s");

        double bmi = sim.evaluateBody(82, 1.78);
        System.out.println("BMI category score: " + bmi);

        double price = sim.finalPrice(1450, 3, true);
        System.out.println("Final price: " + price);
    }

    public double simulateFall(double heightMeters, double durationSeconds) {
        double velocity = 9.81 * durationSeconds;

        if (heightMeters > 100) {
            velocity = velocity * 0.98;
        }

        if (durationSeconds > 10) {
            velocity = velocity - 4.2;
        }

        return Math.round(velocity * 100.0) / 100.0;
    }

    public double evaluateBody(double weightKg, double heightM) {
        double bmi = weightKg / (heightM * heightM);

        if (bmi < 18.5) {
            return 1;
        } else if (bmi < 25) {
            return 2;
        } else if (bmi < 30) {
            return 3;
        } else if (bmi < 35) {
            return 4;
        } else {
            return 5;
        }
    }

    public double finalPrice(double basePrice, int itemCount, boolean member) {
        double price = basePrice;

        if (itemCount >= 5) {
            price = price * 0.9;
        } else if (itemCount >= 3) {
            price = price * 0.95;
        }

        if (member) {
            price = price - 150;
        }

        price = price * 1.21;

        if (price > 5000) {
            price = price - (price * 0.02);
        }

        return Math.round(price);
    }
}