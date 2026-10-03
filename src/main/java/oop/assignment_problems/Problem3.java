import java.util.HashMap;

public class Problem3 {

    static String[] mostPopular(String[] orders) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }
        String bestItem = orders[0];
        int bestCount = 0;
        for (String item : orders) {
            int c = count.get(item);
            if (c > bestCount) {
                bestCount = c;
                bestItem = item;
            }
        }
        return new String[]{bestItem, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {
        String[] o1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        String[] r1 = mostPopular(o1);
        System.out.println("(" + r1[0] + ", " + r1[1] + ")");

        String[] o2 = {"tea", "coffee", "coffee", "tea"};
        String[] r2 = mostPopular(o2);
        System.out.println("(" + r2[0] + ", " + r2[1] + ")");
    }
}
