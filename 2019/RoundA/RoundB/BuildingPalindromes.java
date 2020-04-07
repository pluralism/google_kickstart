import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildingPalindromes {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            in.nextInt();
            final int Q = in.nextInt();
            final String text = in.next();
            final List<List<Integer>> frequencies = buildFrequencies(text);

            int result = 0;
            for (int j = 0; j < Q; j++) {
                final List<Integer> l1 = frequencies.get(in.nextInt() - 1);
                final List<Integer> l2 = frequencies.get(in.nextInt());
                result += isPalindrome(l1, l2) ? 1 : 0;
            }
            System.out.println(String.format("Case #%d: %d", i, result));
        }
    }

    static List<List<Integer>> buildFrequencies(final String text) {
        final List<List<Integer>> frequencies = new ArrayList<>();
        frequencies.add(Stream.generate(() -> 0).limit(26).collect(Collectors.toList()));
        for (int i = 0; i < text.length(); i++) {
            final List<Integer> newList = new ArrayList<>(frequencies.get(i));
            final int index = text.charAt(i) - 'A';
            newList.set(index, newList.get(index) + 1);
            frequencies.add(newList);
        }

        return frequencies;
    }

    static boolean isPalindrome(final List<Integer> l1, final List<Integer> l2) {
        int oddCount = 0;
        for (int i = 0; i < l1.size(); i++) {
            if ((l2.get(i) - l1.get(i)) % 2 != 0) {
                oddCount++;
            }
            if (oddCount > 1) {
                return false;
            }
        }
        return true;
    }
}
