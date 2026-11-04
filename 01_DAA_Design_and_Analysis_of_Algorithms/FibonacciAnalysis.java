import java.util.Scanner;

public class FibonacciAnalysis {
    static int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive integer");
            sc.close();
            return;
        }

        System.out.println("Non-Recursive Fibonacci:");
        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int c = a + b;
            a = b;
            b = c;
        }

        System.out.println("\nRecursive Fibonacci:");
        for (int i = 0; i < n; i++)
            System.out.print(fib(i) + " ");

        sc.close();
    }
}