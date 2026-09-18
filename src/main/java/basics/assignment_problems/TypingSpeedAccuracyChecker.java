public class TypingSpeedAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null || original.length() != typed.length()) {
            System.out.println("Original and typed text must have equal lengths");
            return;
        }

        int matchedCharacters = 0;
        int firstMismatch = -1;
        for (int index = 0; index < original.length(); index++) {
            if (original.charAt(index) == typed.charAt(index)) {
                matchedCharacters++;
            } else if (firstMismatch == -1) {
                firstMismatch = index;
            }
        }

        double accuracy = original.isEmpty() ? 100.0
                : matchedCharacters * 100.0 / original.length();
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matchedCharacters, original.length(), accuracy);
        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.printf(" | First Mismatch at position %d ('%c' vs '%c')%n",
                    firstMismatch + 1, original.charAt(firstMismatch), typed.charAt(firstMismatch));
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}