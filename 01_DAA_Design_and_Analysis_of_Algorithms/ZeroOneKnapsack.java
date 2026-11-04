
import java.util.*;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] w = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and profit: ");
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int c = sc.nextInt();

        int[] dp = new int[c + 1];

        for (int i = 0; i < n; i++) {
            for (int j = c; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], p[i] + dp[j - w[i]]);
            }
        }

        System.out.println("Maximum Profit = " + dp[c]);

        sc.close();
    }
}
