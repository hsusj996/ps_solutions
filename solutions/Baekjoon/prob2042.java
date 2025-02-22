import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    static int leafLayer;
    static int leafStartIdx;
    static StringBuilder sb = new StringBuilder();
    static int treeSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[(int) N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        treeInit();

        // printForDebug();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int opt = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (opt == 1) {
                updateQuery(a, b);
            } else {
                Long sum = sumQuery(a, (int) b);

                sb.append(sum).append("\n");
            }

            // printForDebug();
        }

        System.out.println(sb.toString());
    }

    private static void printForDebug() {
        System.out.println("\n========tree========");

        StringBuilder sbForDebug = new StringBuilder();
        for (int i = 0; Math.pow(2, i) < treeSize; i++) {
            for (int j = (int) Math.pow(2, i); j < Math.pow(2, i + 1); j++) {
                sbForDebug.append(tree[j]).append(" ");
            }
            sbForDebug.append("\n");
        }

        System.out.println(sbForDebug.toString());
    }

    private static void treeInit() {
        int idx = 1;
        while (Math.pow(2, idx) <= N) {
            idx++;
        }
        leafLayer = idx;
        treeSize = (int) Math.pow(2, leafLayer + 1);
        tree = new long[treeSize];
        leafStartIdx = (int) Math.pow(2, leafLayer);

        for (int i = 0; i < N; i++) {
            tree[leafStartIdx + i] = arr[i];
        }

        if (N > 1) {
            tree[1] = (DP(2) + DP(3));
        }
    }

    private static long DP(int idx) {
        if (idx >= leafStartIdx) {
            return tree[idx];
        }

        if (tree[idx] == 0) {
            tree[idx] = DP(idx * 2) + DP(idx * 2 + 1);
        }

        return tree[idx];
    }

    private static Long sumQuery(int a, int b) {
        int right = (int) Math.pow(2, leafLayer);
        long ret = recursiveSum(1, 1, right, a, b);

        return ret;
    }

    private static Long recursiveSum(int n, int left, int right, int a, int b) {
        if (a <= left && right <= b) {
            return tree[n];
        } else if (right < a || left > b) {
            return (long) 0;
        } else {
            int mid = (left + right) / 2;
            return recursiveSum(n * 2, left, mid, a, b) + recursiveSum(n * 2 + 1, mid + 1, right, a, b);
        }
    }

    private static void updateQuery(int a, long b) {
        int nodeIdx = (leafStartIdx + a - 1);
        long originNum = tree[nodeIdx];
        long diff = (b - originNum);

        tree[nodeIdx] = (long) b;
        int idx = (nodeIdx / 2);
        while (idx >= 1) {
            tree[idx] += diff;
            idx /= 2;
        }
    }
}
