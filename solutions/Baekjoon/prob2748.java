package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob2748 {
    static long[] DP;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        DP = new long[N + 1];
        DP[0] = 0;
        DP[1] = 1;


        for(int i=2;i<=N;i++){
            DP[i] = DP[i-1] + DP[i-2];
        }

        System.out.println(DP[N]);
    }
}
