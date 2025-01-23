import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob11497_2 {
    static int T;
    static int N;
    static StringTokenizer st = null;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int min = arr[1] - arr[0];
            int max = arr[arr.length - 1] - arr[0];

            int ans = greedy(min, max, arr);
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int greedy(int min, int max, int[] targetArr) {
        for (int v = min; v <= max; v++) {
            boolean flag = isPossibleValue(v, targetArr);

            if (flag) {
                return v;
            }
        }
        return max;
    }

    private static boolean isPossibleValue(int v, int[] targetArr) {
        boolean[] visited = new boolean[N];
        int idx = 0;
        visited[idx] = true;
        while (idx < N - 1) {
            int next = -1;
            for (int i = idx + 1; i < N; i++) {
                if ((targetArr[i] - targetArr[idx]) > v) {
                    next = i - 1;
                    break;
                } else if (i == N - 1) {
                    next = i;
                    break;
                }
            }

            if (next == -1 || next == idx) {
                return false;
            } else {
                idx = next;
                visited[next] = true;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }

            if ((targetArr[idx] - targetArr[i]) <= v) {
                idx = i;
            } else {
                return false;
            }
        }

        if ((targetArr[idx] - targetArr[0]) <= v) {
            return true;
        } else {
            return false;
        }
    }
}
