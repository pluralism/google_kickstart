import java.util.*;

public class Training {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int P = in.nextInt();
            final List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                numbers.add(in.nextInt());
            }
            System.out.println(String.format("Case #%d: %d", i, calculate(numbers, P)));
        }
    }

    static int calculate(final List<Integer> numbers, final int P) {
        numbers.sort(Collections.reverseOrder());
        int minimumHours = Integer.MAX_VALUE;
        int[] sum = new int[numbers.size() + 1];
        sum[0] = 0;

        for (int i = 0; i < numbers.size(); i++) {
            sum[i + 1] = sum[i] + numbers.get(i);
        }

        for (int i = P - 1; i < numbers.size(); i++) {
            minimumHours = Math.min(minimumHours, (P - 1) * numbers.get(i - P + 1) - (sum[i + 1] - sum[i - P + 2]));
        }

        return minimumHours;
    }
}
