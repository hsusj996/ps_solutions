import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];
        int sum = 0;
        for(int i=0;i<5;i++){
            arr[i] = sc.nextInt();
            if(arr[i] < 40) arr[i] = 40;
            sum += arr[i];
        }

        System.out.println(sum / 5);

        sc.close();
    }
}
