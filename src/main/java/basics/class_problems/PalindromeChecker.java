public class PalindromeChecker {
    public static boolean isPalindromeIterative(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) {
            return false;
        }
        return isPalindromeRecursive(text, 0, text.length() - 1);
    }

    private static boolean isPalindromeRecursive(String text, int left, int right) {
        if (left >= right) {
            return true;
        }
        return text.charAt(left) == text.charAt(right)
                && isPalindromeRecursive(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) {
            return false;
        }
        char[] original = text.toCharArray();
        char[] reversed = original.clone();
        for (int left = 0, right = reversed.length - 1; left < right; left++, right--) {
            char temporary = reversed[left];
            reversed[left] = reversed[right];
            reversed[right] = temporary;
        }
        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {
        String[] inputs = {"madam", "hello"};
        for (String input : inputs) {
            System.out.printf("%s -> Iterative: %s | Recursive: %s | Array Reversal: %s%n",
                    input, resultLabel(isPalindromeIterative(input)),
                    resultLabel(isPalindromeRecursive(input)),
                    resultLabel(isPalindromeArrayReversal(input)));
        }
    }

    private static String resultLabel(boolean palindrome) {
        return palindrome ? "Palindrome" : "Not Palindrome";
    }
}