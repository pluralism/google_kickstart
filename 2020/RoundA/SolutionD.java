import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionD {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int T = in.nextInt();

        for (int i = 1; i <= T; i++) {
            final int N = in.nextInt();
            final int K = in.nextInt();
            final List<String> words = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                words.add(in.next());
            }
            final SolutionD.Trie trie = new SolutionD.Trie();
            words.forEach(trie::insert);
            System.out.println(String.format("Case #%d: %d", i, new SolutionD.Solver(trie.root, K).run()));
        }
    }

    static class Trie {
        static class TrieNode {
            private static final int ALPHABET_SIZE = 26;
            private final TrieNode[] children;
            private int count;

            public TrieNode() {
                this.children = new TrieNode[ALPHABET_SIZE];
                for (int i = 0; i < ALPHABET_SIZE; i++) {
                    children[i] = null;
                }
                this.count = 0;
            }
        }

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(final String word) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                final int index = word.charAt(i) - 'A';
                if (currentNode.children[index] == null) {
                    currentNode.children[index] = new TrieNode();
                }
                currentNode = currentNode.children[index];
            }
            currentNode.count++;
        }
    }

    static class Solver {
        private Trie.TrieNode node;
        private final int K;
        private int answer = 0;

        public Solver(final Trie.TrieNode root, final int K) {
            this.node = root;
            this.K = K;
        }

        public int run() {
            return dfs(node, K, 0);
        }

        private int dfs(final Trie.TrieNode node, final int K, final int depth) {
            for (int i = 0; i < node.children.length; i++) {
                final Trie.TrieNode child = node.children[i];
                if (child != null) {
                    dfs(child, K, depth + 1);
                    node.count += child.count;
                }
            }

            while (node.count >= K) {
                node.count -= K;
                answer += depth;
            }

            return answer;
        }
    }
}
