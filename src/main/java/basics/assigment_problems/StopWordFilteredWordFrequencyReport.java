package basics.assigment_problems;

import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    public void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = new HashSet<>(Arrays.asList(
                "the", "was", "and", "a", "is", "of", "in"));

        String normalized = feedback.toLowerCase();
        normalized = normalized.replace(".", "").replace(",", "");

        String[] words = normalized.split("\\s+");
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            if (stopWords.contains(word)) {
                continue;
            }
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        StopWordFilteredWordFrequencyReport report = new StopWordFilteredWordFrequencyReport();
        report.printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}