package basics.class_problems;

import java.util.Random;

public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI   | Status");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            double roundedBmi = Math.round(bmi * 100.0) / 100.0;

            System.out.println("Person " + (i + 1) + " | " + heights[i] + "       | " + weights[i] + "         | " + roundedBmi + " | " + status);
        }
    }

    public static void main(String[] args) {
        int teamSize = 10;
        double[] heights = new double[teamSize];
        double[] weights = new double[teamSize];

        Random rand = new Random();

        for (int i = 0; i < teamSize; i++) {
            heights[i] = 1.50 + rand.nextInt(40) / 100.0;
            weights[i] = 50 + rand.nextInt(50);
        }

        printWellnessReport(heights, weights);
    }
}
