import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SolutionA {
    public void main() {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int B = in.nextInt();
            final List<Integer> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                list.add(in.nextInt());
            }
            list.sort(Comparator.naturalOrder());
            int houses = 0;
            int sum = 0;
            for (final Integer n : list) {
                if (sum + n <= B) {
                    houses++;
                    sum += n;
                } else {
                    break;
                }
            }
            System.out.println(String.format("Case #%d: %d", i, houses));
        }
    }
}
