import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();
        final long GRID_SIZE = 1000000000;

        for (int i = 1; i <= T; i++) {
            final String s = in.next();
            final Stack<Long> multiplier = new Stack<>();
            final Stack<Tuple<Long>> pairs = new Stack<>();
            Tuple<Long> total = new Tuple<>(0L, 0L);
            multiplier.push(1L);
            for (int j = 0; j < s.length(); j++) {
                final char ch = s.charAt(j);
                if (Character.isDigit(ch)) {
                    multiplier.push((long)(ch - '0'));
                    pairs.push(total);
                    total = new Tuple<>(0L, 0L);
                }
                if (ch == '(') continue;
                if (ch == ')') {
                    total.x = (total.x * multiplier.peek()) % GRID_SIZE;
                    total.y = (total.y * multiplier.peek()) % GRID_SIZE;
                    multiplier.pop();
                    final Tuple<Long> top = pairs.pop();
                    total = new Tuple<>((top.x + total.x) % GRID_SIZE, (top.y + total.y) % GRID_SIZE);
                    continue;
                }
                if (ch == 'N') total.y--;
                if (ch == 'S') total.y++;
                if (ch == 'E') total.x++;
                if (ch == 'W') total.x--;
            }
            total.x++;
            total.y++;
            total.x = total.x <= 0 ? total.x + GRID_SIZE : total.x;
            total.y = total.y <= 0 ? total.y + GRID_SIZE : total.y;
            System.out.println(String.format("Case #%d: %d %d", i, total.x, total.y));
        }
    }

    static class Tuple<T> {
        public T x;
        public T y;

        Tuple(final T x, final T y) {
            this.x = x;
            this.y = y;
        }
    }
}
