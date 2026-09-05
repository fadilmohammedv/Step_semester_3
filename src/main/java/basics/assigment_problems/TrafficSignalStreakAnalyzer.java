package basics.assigment_problems;

public class TrafficSignalStreakAnalyzer {

    public static void findLongestStreak(String signalLog) {
        int longestCount = 1;
        char longestChar = signalLog.charAt(0);

        int currentCount = 1;
        char currentChar = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);

            if (c == currentChar) {
                currentCount++;
            } else {
                currentChar = c;
                currentCount = 1;
            }

            if (currentCount > longestCount) {
                longestCount = currentCount;
                longestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestCount + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
