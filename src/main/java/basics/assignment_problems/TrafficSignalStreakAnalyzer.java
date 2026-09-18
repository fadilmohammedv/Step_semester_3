public class TrafficSignalStreakAnalyzer {
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("No Signal Readings");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestLength = 1;
        char currentColor = longestColor;
        int currentLength = 1;

        for (int index = 1; index < signalLog.length(); index++) {
            if (signalLog.charAt(index) == currentColor) {
                currentLength++;
            } else {
                currentColor = signalLog.charAt(index);
                currentLength = 1;
            }
            if (currentLength > longestLength) {
                longestColor = currentColor;
                longestLength = currentLength;
            }
        }

        System.out.println("Longest Streak: '" + longestColor + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYG");
    }
}