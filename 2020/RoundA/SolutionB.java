import java.util.*;

public class SolutionB {
    public void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int K = in.nextInt();
            final int P = in.nextInt();

            final List<List<Integer>> stacks = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                final List<Integer> stack = new ArrayList<>();
                for (int k = 0; k < K; k++) {
                    stack.add(in.nextInt());
                }
                stacks.add(stack);
            }
            System.out.println(String.format("Case #%d: %d", i, calculate(stacks, N, K, P)));
        }
    }

    public int calculate(List<List<Integer>> stacks, int N, int K, int P) {
        int[][] sums = new int[stacks.size() + 1][stacks.get(0).size() + 1];
        for (int i = 1; i <= stacks.size(); i++) {
            int sum = 0;
            for(int j = 1; j <= stacks.get(i - 1).size(); j++) {
                sum += stacks.get(i - 1).get(j - 1);
                sums[i][j] = sum;
            }
        }

        int[][] dp = new int[N + 1][P + 1];
        for (int i = 0; i <= P; i++) {
            dp[0][i] = 0;
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= P; j++) {
                for (int k = 0; k <= Math.min(j, K); k++) {
                    dp[i][j] = Math.max(dp[i][j], sums[i][k] + dp[i - 1][j - k]);
                }
            }
        }

        return dp[N][P];
    }
}
