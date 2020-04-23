import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int[] arr = new int[N];
            for (int j = 0; j < N; j++) arr[j] = in.nextInt();
            int peaks = 0;
            for (int j = 1; j < N - 1; j++) if (arr[j] > arr[j - 1] && arr[j] > arr[j + 1]) peaks++;
            System.out.println(String.format("Case #%d: %d", i, peaks));
        }
    }
}
