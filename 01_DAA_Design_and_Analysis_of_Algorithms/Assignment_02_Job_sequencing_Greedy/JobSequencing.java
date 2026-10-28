
import java.util.Arrays;
import java.util.Comparator;

public class JobSequencing {

    static class Job {
        char id;
        int deadline;
        int profit;

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {

        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };

        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job j1, Job j2) {
                return Integer.compare(j2.profit, j1.profit);
            }
        });

        int maxDeadline = 0;

        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        char[] schedule = new char[maxDeadline + 1];
        boolean[] filled = new boolean[maxDeadline + 1];
        int totalProfit = 0;

        for (int i = 0; i < jobs.length; i++) {
            for (int slot = jobs[i].deadline; slot >= 1; slot--) {
                if (!filled[slot]) {
                    schedule[slot] = jobs[i].id;
                    filled[slot] = true;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        System.out.println("Selected Jobs:");

        for (int slot = 1; slot <= maxDeadline; slot++) {
            if (filled[slot]) {
                System.out.print(schedule[slot] + " ");
            }
        }

        System.out.println("\nMaximum Profit: " + totalProfit);
    }
}
