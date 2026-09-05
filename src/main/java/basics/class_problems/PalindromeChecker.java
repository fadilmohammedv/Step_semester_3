package basics.class_problems;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
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
        if (text.length() <= 1) {
            return true;
        }

        char first = text.charAt(0);
        char last = text.charAt(text.length() - 1);

        if (first != last) {
            return false;
        }

        String smaller = text.substring(1, text.length() - 1);
        return isPalindromeRecursive(smaller);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] chars = text.toCharArray();
        char[] reversedChars = new char[chars.length];

        int index = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            reversedChars[i] = chars[index];
            index--;
        }

        String reversedText = new String(reversedChars);
        return text.equals(reversedText);
    }

    public static void main(String[] args) {
        String[] testWords = {"madam", "hello"};

        for (String word : testWords) {
            boolean iterative = isPalindromeIterative(word);
            boolean recursive = isPalindromeRecursive(word);
            boolean arrayReversal = isPalindromeArrayReversal(word);

            System.out.println("Input: \"" + word + "\"");
            System.out.println("Iterative: " + (iterative ? "Palindrome" : "Not Palindrome") +
                    " | Recursive: " + (recursive ? "Palindrome" : "Not Palindrome") +
                    " | Array Reversal: " + (arrayReversal ? "Palindrome" : "Not Palindrome"));
            System.out.println();
        }
    }
}
