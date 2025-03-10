import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int[] minTree;
    static int[] maxTree;
    static int N, M;
    static StringTokenizer st = null;
    static int leafLayer;
    static int leafStartIdx;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minTree = new int[N * 4];
        maxTree = new int[N * 4];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        treeInit();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(getMinMax(a, b)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static String getMinMax(int a, int b) {
        int min = recursiveMin(1, 1, N, a, b);
        int max = recursiveMax(1, 1, N, a, b);

        return min + " " + max;
    }

    private static int recursiveMax(int n, int left, int right, int a, int b) {
        if (a <= left && right <= b) {
            return maxTree[n];
        } else if (right < a || left > b) {
            return Integer.MIN_VALUE;
        } else {
            int mid = (left + right) / 2;
            return Math.max(recursiveMax(n * 2, left, mid, a, b), recursiveMax(n * 2 + 1, mid + 1, right, a, b));
        }
    }

    private static int recursiveMin(int n, int left, int right, int a, int b) {
        if (a <= left && right <= b) {
            return minTree[n];
        } else if (right < a || left > b) {
            return Integer.MAX_VALUE;
        } else {
            int mid = (left + right) / 2;
            return Math.min(recursiveMin(n * 2, left, mid, a, b), recursiveMin(n * 2 + 1, mid + 1, right, a, b));
        }
    }

    private static void treeInit() {
        int idx = 0;
        while (Math.pow(2, idx) <= N) {
            idx++;
        }
        leafLayer = idx;
        leafStartIdx = (int) Math.pow(2, leafLayer);
        for (int i = 0; i < N; i++) {
            maxTree[leafStartIdx + i] = minTree[leafStartIdx + i] = arr[i];
        }

        if (N > 1) {
            minTree[1] = minInit(1, N, 1);
            maxTree[1] = maxInit(1, N, 1);
        }
    }

    private static int maxInit(int start, int end, int n) {
        if (start == end) {
            return maxTree[n] = arr[start];
        }

        int mid = (start + end) / 2;
        return maxTree[n] = Math.max(maxInit(start, mid, n * 2), maxInit(mid + 1, end, n * 2 + 1));
    }

    private static int minInit(int start, int end, int n) {
        if (start == end) {
            return minTree[n] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[n] = Math.min(minInit(start, mid, n * 2), minInit(mid + 1, end, n * 2 + 1));
    }
}