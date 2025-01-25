import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    static int N;
    static xy[] lines;
    static class xy implements Comparable<xy> {
        int x;
        int y;
        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(xy o) {
            return this.x - o.x;
        }
        
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        lines = new xy[N];
        for(int i=0;i<N;i++){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);

            lines[i] = new xy(x, y);
        }

        Arrays.sort(lines);

        int ans = 0;
        int start = lines[0].x;
        int end = lines[0].y;
        for(int i=1;i<N;i++){
            int x = lines[i].x;
            int y = lines[i].y;

            if(x <= end){
                if(y <= end){
                    continue;
                } else{
                    end = y;
                }
            } else{
                ans += (end - start);
                start = x;
                end = y;
            }
        }

        ans += (end - start);
        System.out.println(ans);
    }
}
