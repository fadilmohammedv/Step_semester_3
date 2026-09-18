public class TeamBmiCalculator {
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        }
        if (bmi < 25.0) {
            return "Normal";
        }
        if (bmi < 30.0) {
            return "Overweight";
        }
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights == null || weights == null || heights.length != weights.length) {
            throw new IllegalArgumentException("Heights and weights must have equal lengths");
        }

        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        for (int index = 0; index < heights.length; index++) {
            if (heights[index] <= 0 || weights[index] < 0) {
                throw new IllegalArgumentException("Height must be positive and weight cannot be negative");
            }
            double bmi = weights[index] / (heights[index] * heights[index]);
            System.out.printf("Person %d | %.2f | %.1f | %.2f | %s%n",
                    index + 1, heights[index], weights[index], bmi, getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        double[] heights = {1.75, 1.60};
        double[] weights = {70, 90};
        printWellnessReport(heights, weights);
    }
}