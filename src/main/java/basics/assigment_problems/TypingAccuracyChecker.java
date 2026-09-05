package basics.assigment_problems;

public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatchPosition = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPosition == -1) {
                firstMismatchPosition = i;
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;
        double roundedAccuracy = Math.round(accuracy * 100.0) / 100.0;

        System.out.print("Matched: " + matched + "/" + original.length() + " | Accuracy: " + roundedAccuracy + "% | ");

        if (firstMismatchPosition == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + (firstMismatchPosition + 1) +
                    " ('" + original.charAt(firstMismatchPosition) + "' vs '" + typed.charAt(firstMismatchPosition) + "')");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
