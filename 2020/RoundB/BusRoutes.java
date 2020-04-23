import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final long[] dates = new long[N];
            long D = in.nextInt();
            for (int j = 0; j < N; j++) dates[j] = in.nextLong();
            for (int j = dates.length - 1; j >= 0; j--) D = dates[j] * (D / dates[j]);
            System.out.println(String.format("Case #%d: %d", i, D));
        }
    }
}
