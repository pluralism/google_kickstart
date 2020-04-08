import java.util.*;

public class EnergyStones {
    static class EnergyStone implements Comparable<EnergyStone> {
        int secondsToEat;
        int totalEnergy;
        int energyLostRate;

        EnergyStone(int secondsToEat, int totalEnergy, int energyLostRate) {
            this.secondsToEat = secondsToEat;
            this.totalEnergy = totalEnergy;
            this.energyLostRate = energyLostRate;
        }

        @Override
        public int compareTo(final EnergyStone other) {
            return Integer.compare(this.secondsToEat * other.energyLostRate, other.secondsToEat * this.energyLostRate);
        }
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final List<EnergyStone> energyStones = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                energyStones.add(new EnergyStone(in.nextInt(), in.nextInt(), in.nextInt()));
            }
            System.out.println(String.format("Case #%d: %d", i, solve(energyStones)));
        }
    }

    static int solve(final List<EnergyStone> energyStones) {
        Collections.sort(energyStones);
        final int maxTime = energyStones
                .stream()
                .reduce(0, (subTotal, element) -> subTotal + element.secondsToEat, Integer::sum);
        final int[][] dp = new int[energyStones.size() + 1][maxTime + 1];

        int result = 0;
        for (int i = 1; i <= energyStones.size(); i++) {
            final EnergyStone stone = energyStones.get(i - 1);

            for (int j = 0; j < stone.secondsToEat; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            for (int j = stone.secondsToEat; j <= maxTime; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stone.secondsToEat] + Math.max(stone.totalEnergy - (j - stone.secondsToEat) * stone.energyLostRate, 0));
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}
