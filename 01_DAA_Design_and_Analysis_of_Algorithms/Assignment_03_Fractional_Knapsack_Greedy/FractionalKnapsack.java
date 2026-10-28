
import java.util.*;

class Item {
    int profit, weight;
    double ratio;

    Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        this.ratio = (double) profit / weight;
    }
}

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of items: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid input: At least one item is required.");
            sc.close();
            return;
        }

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Profit and weight of item " + (i + 1) + ": ");
            int profit = sc.nextInt();
            int weight = sc.nextInt();

            if (profit < 0 || weight <= 0) {
                System.out.println("Invalid item data: Profit must be non-negative and weight must be positive.");
                sc.close();
                return;
            }

            items[i] = new Item(profit, weight);
        }

        System.out.print("Knapsack capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Invalid capacity: Knapsack capacity cannot be negative.");
            sc.close();
            return;
        }

        if (capacity == 0) {
            System.out.println("Maximum profit: 0.00");
            System.out.println("No items can be selected because the knapsack has zero capacity.");
            sc.close();
            return;
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0;
        int remaining = capacity;

        for (int i = 0; i < n && remaining > 0; i++) {
            if (items[i].weight <= remaining) {
                remaining -= items[i].weight;
                totalProfit += items[i].profit;
            } else {
                totalProfit += items[i].profit *
                        ((double) remaining / items[i].weight);
                remaining = 0;
            }
        }

        System.out.printf("Maximum profit using the Greedy method: %.2f%n",
                totalProfit);

        if (remaining == 0) {
            System.out.println("Knapsack capacity utilized completely.");
        } else {
            System.out.println("Knapsack capacity utilized partially.");
        }

        sc.close();
    }
}
