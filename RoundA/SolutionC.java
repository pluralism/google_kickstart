import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SolutionC {
    public void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int K = in.nextInt();
            final List<Long> M = new ArrayList<>();
            IntStream.range(0, N).forEach(n -> M.add(in.nextLong()));
            System.out.println(String.format("Case #%d: %d", i, calculate(M, K)));
        }
    }

    long calculate(final List<Long> M, final int K) {
        long low = 1;
        long high = (long) 10e9;

        while (low < high) {
            final long dOptiomal = (low + high) / 2;
            final long sumK = IntStream
                    .range(1, M.size())
                    .mapToLong(i -> (M.get(i) - M.get(i - 1) - 1) / dOptiomal).sum();

            if (sumK > K) {
                low = dOptiomal + 1;
            } else {
                high = dOptiomal;
            }
        }

        return low;
    }
}
