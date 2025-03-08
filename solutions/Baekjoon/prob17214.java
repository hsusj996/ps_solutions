import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        // check x
        boolean xflag = false;
        int xindex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'x') {
                xflag = true;
                xindex = i;
                break;
            }
        }

        int a = 0;
        int b = 0;
        if (xflag) {
            String coef = s.substring(0, xindex);
            if(xindex < s.length() - 1){
                String constant = s.substring(xindex + 1, s.length());
                b = Integer.parseInt(constant);
            }

            a = Integer.parseInt(coef);
        } else {
            b = Integer.parseInt(s);
        }

        StringBuilder sb = new StringBuilder();
        if (a != 0) {
            if (a / 2 < 0) {
                sb.append("-");
            }

            if (Math.abs(a / 2) != 1) {
                sb.append(Math.abs(a / 2));
            }

            sb.append("xx");
        }

        if (b != 0) {
            if (b < 0) {
                sb.append("-");
            } else if(a != 0){
                sb.append("+");
            }

            if (Math.abs(b) != 1) {
                sb.append(Math.abs(b));
            }

            sb.append("x");
        }

        if(a != 0 || b != 0){
            sb.append("+W");
        } else{
            sb.append("W");
        }

        System.out.println(sb.toString());
    }
}