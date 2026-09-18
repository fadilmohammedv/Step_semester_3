import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StopWordFilteredFrequencyReport {
    private static final Set<String> STOP_WORDS = new HashSet<>();

    static {
        STOP_WORDS.add("the");
        STOP_WORDS.add("was");
        STOP_WORDS.add("and");
        STOP_WORDS.add("a");
        STOP_WORDS.add("is");
        STOP_WORDS.add("of");
        STOP_WORDS.add("in");
    }

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        String normalized = feedback.toLowerCase()
                .replace(",", "")
                .replace(".", "");
        String[] words = normalized.trim().split("\\s+");
        Map<String, Integer> frequencies = new LinkedHashMap<>();

        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencies.entrySet());
        entries.sort(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed());
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}