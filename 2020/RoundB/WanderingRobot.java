import java.util.Scanner;

public class Solution {
    final static int MAX_N = (int)10e5;
    final static double[] logarithmicFactorials = new double[MAX_N];

    static double binomialCoeff(final int n, final int k) {
        /**
         * C(n, k) = n! / (k! * (n - k)!)
         * Because we're using logarithms then we can conclude that log2(n! / (k! * (n - k)!))
         * is equal to log2(n!) - (log2(k!) + log2((n - k)!)), which in turn is equal to
         * log2(n!) - log2(k!) - log2((n - k)!).
         * That is the expression that is being returned by this function!
         */
        return logarithmicFactorials[n] - logarithmicFactorials[k] - logarithmicFactorials[n - k];
    }

    public static void main(String[] args) {
        for (int n = 2; n < MAX_N; n++) {
            // logarithmicFactorials[n] is the value of log2(n!)
            logarithmicFactorials[n] = logarithmicFactorials[n - 1] + (Math.log(n) / Math.log(2));
        }
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int W = in.nextInt();
            final int H = in.nextInt();
            final int L = in.nextInt();
            final int U = in.nextInt();
            final int R = in.nextInt();
            final int D = in.nextInt();

            double result = 0;
            if (D < H) result += getResult(L, D);
            if (R < W) result += getResult(U, R);
            System.out.println(String.format("Case #%d: %f", i, result));
        }
    }

    private static double getResult(final int W, final int H) {
        double result = 0;
        final int dist = H + W - 2; // H - 1 + W - 1 = H + W - 2
        for (int j = 1; j < W; j++) {
            result += Math.pow(2, binomialCoeff(dist, j - 1) - dist);
        }
        return result;
    }
}
