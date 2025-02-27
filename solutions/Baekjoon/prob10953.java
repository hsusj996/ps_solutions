import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    static int N;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String s = br.readLine();
            String[] arr = s.split(",");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);

            sb.append(a + b).append("\n");
        }
        System.out.println(sb.toString());
    }
}