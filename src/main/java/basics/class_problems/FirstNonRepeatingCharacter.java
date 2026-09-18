import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {
    public static String findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return "No Non-Repeating Character Found";
        }

        Map<Character, Integer> frequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        for (char character : text.toCharArray()) {
            if (frequencies.get(character) == 1) {
                return "First Non-Repeating Character: '" + character + "'";
            }
        }
        return "No Non-Repeating Character Found";
    }

    public static void main(String[] args) {
        System.out.println(findFirstNonRepeatingChar("swiss"));
        System.out.println(findFirstNonRepeatingChar("aabbcc"));
    }
}