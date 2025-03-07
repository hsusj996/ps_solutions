import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static int N, M;
    static String[] board;
    static int[] zeroCountArr;
    static int K;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new String[N];
        zeroCountArr = new int[N];
        // count zero
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == '0') {
                    zeroCountArr[i]++;
                }
            }
        }

        K = Integer.parseInt(br.readLine());

        // check board
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (zeroCountArr[i] <= K && (zeroCountArr[i] - K) % 2 == 0) {
                int sameRawCnt = 1;
                for (int j = 0; j < N; j++) {
                    if (j == i)
                        continue;

                    if (board[i].equals(board[j])) {
                        sameRawCnt++;
                    }
                }

                max = Math.max(max, sameRawCnt);
            }
        }

        System.out.println(max);
    }
}