import java.util.Scanner;

public class App{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int w = sc.nextInt();
        int h = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();
        int t = sc.nextInt();

        int remainW = (t + p) % (2 * w);
        int remainH = (t + q) % (2 * h);

        if (remainW > w) {
            remainW = 2 * w - remainW;
        }
        if (remainH > h) {
            remainH = 2 * h - remainH;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(remainW).append(" ").append(remainH);
        System.out.println(sb.toString());
    }
}