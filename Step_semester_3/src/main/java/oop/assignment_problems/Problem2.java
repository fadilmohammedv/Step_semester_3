import java.util.Arrays;

public class Problem2 {

    static int[] mergeTokens(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];
            }
        }
        while (i < a.length) {
            res[k++] = a[i++];
        }
        while (j < b.length) {
            res[k++] = b[j++];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a1 = {3, 8, 15, 20};
        int[] b1 = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(a1, b1)));

        int[] a2 = {};
        int[] b2 = {4, 9};
        System.out.println(Arrays.toString(mergeTokens(a2, b2)));
    }
}
