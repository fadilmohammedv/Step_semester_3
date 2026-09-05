package basics.class_problems;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            frequency[c]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (frequency[c] == 1) {
                return c;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        String[] tests = {"swiss", "aabbcc"};

        for (String test : tests) {
            char result = findFirstNonRepeatingChar(test);

            System.out.println("Input: \"" + test + "\"");
            if (result == '\0') {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
            System.out.println();
        }
    }
}
