
import java.util.Scanner;

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, capacity;

        System.out.print("Enter number of items: ");
        n = sc.nextInt();

        int[] w = new int[n];
        int[] p = new int[n];
        double[] r = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and profit: ");
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
            r[i] = (double) p[i] / w[i];
        }

        System.out.print("Enter capacity: ");
        capacity = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (r[i] < r[j]) {
                    double temp = r[i];
                    r[i] = r[j];
                    r[j] = temp;

                    int t = w[i];
                    w[i] = w[j];
                    w[j] = t;

                    t = p[i];
                    p[i] = p[j];
                    p[j] = t;
                }
            }
        }

        double profit = 0;

        for (int i = 0; i < n; i++) {
            if (w[i] <= capacity) {
                profit += p[i];
                capacity -= w[i];
            } else {
                profit += r[i] * capacity;
                break;
            }
        }

        System.out.println("Maximum Profit: " + profit);

        sc.close();
    }
}
