import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Parcels {
    static class Pair {
        private final int x;
        private final int y;

        Pair(final int y, final int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            final Queue<Pair> Q = new ArrayDeque<>();
            final int R = in.nextInt();
            final int C = in.nextInt();
            final boolean[][] used = new boolean[R][C];
            for (int j = 0; j < R; j++) {
                final String row = in.next();

                for (int k = 0; k < row.length(); k++) {
                    if (row.charAt(k) == '1') {
                        used[j][k] = true;
                        Q.add(new Pair(j, k));
                    }
                }
            }
            System.out.println(String.format("Case #%d: %d", i, solve(used, Q, R, C)));
        }
    }

    static int solve(boolean[][] used, final Queue<Pair> Q, final int R, final int C) {
        final int[] dx = { 0, 1, 0, -1 };
        final int[] dy = { -1, 0, 1, 0 };
        final int[][] distance = new int[R][C];

        int l = 0;
        int r = 0;

        while (!Q.isEmpty()) {
            final Pair p = Q.poll();
            for (int i = 0; i < 4; i++) {
                final Pair pair = new Pair(p.y + dy[i], p.x + dx[i]);
                if (isValidPosition(R, C, used, pair)) {
                    distance[pair.y][pair.x] = distance[p.y][p.x] + 1;
                    r = Math.max(r, distance[pair.y][pair.x]);
                    used[pair.y][pair.x] = true;
                    Q.add(pair);
                }
            }
        }

        while (l < r) {
            final int mid = (l + r) / 2;
            if (check(mid, R, C, distance)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    static boolean check(final int K, final int R, final int C, final int[][] distance) {
        boolean found = false;
        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        int maxB = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (distance[i][j] > K) {
                    minA = Math.min(minA, i + j);
                    minB = Math.min(minB, i - j);
                    maxA = Math.max(maxA, i + j);
                    maxB = Math.max(maxB, i - j);
                    found = true;
                }
            }
        }

        if (!found) {
            return true;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                final int a1 = Math.abs(maxA - (i + j));
                final int a2 = Math.abs(minA - (i + j));
                final int a3 = Math.abs(maxB - (i - j));
                final int a4 = Math.abs(minB - (i - j));
                if (distance[i][j] > 0 && Math.max(Math.max(a1, a2), Math.max(a3, a4)) <= K) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isValidPosition(final int R, final int C, boolean[][] used, final Pair pair) {
        return pair.x >= 0 && pair.y >= 0 && pair.y < R && pair.x < C && !used[pair.y][pair.x];
    }
}
