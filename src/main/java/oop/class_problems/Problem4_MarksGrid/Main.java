import java.util.*;

public class Main {
    static final int SUBJECTS = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> rows = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int open = line.indexOf('[');
            int close = line.indexOf(']');
            names.add(line.substring(0, open).trim());
            String[] parts = line.substring(open + 1, close).split(",");
            int[] row = new int[SUBJECTS];
            for (int i = 0; i < SUBJECTS; i++) {
                row[i] = Integer.parseInt(parts[i].trim());
            }
            rows.add(row);
        }

        int n = rows.size();
        int[][] marks = rows.toArray(new int[n][]);
        int[] totals = new int[n];
        double[] subjectSum = new double[SUBJECTS];
        int topper = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < SUBJECTS; j++) {
                totals[i] += marks[i][j];
                subjectSum[j] += marks[i][j];
            }
            if (totals[i] > totals[topper]) topper = i;
        }

        StringBuilder sb = new StringBuilder("Totals ");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(", ");
            sb.append(names.get(i)).append(" ").append(totals[i]);
        }
        System.out.println(sb);

        StringBuilder avg = new StringBuilder("averages ");
        for (int j = 0; j < SUBJECTS; j++) {
            if (j > 0) avg.append(", ");
            avg.append(String.format(Locale.US, "%.2f", subjectSum[j] / n));
        }
        System.out.println(avg);

        System.out.println("topper " + names.get(topper) + " (" + totals[topper] + ")");
    }
}
