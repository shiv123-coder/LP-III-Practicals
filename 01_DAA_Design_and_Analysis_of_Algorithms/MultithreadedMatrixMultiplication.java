
public class MultithreadedMatrixMultiplication {

    static int[][] A = {
            { 2, 1, 3 },
            { 4, 0, 2 }
    };

    static int[][] B = {
            { 5, 2 },
            { 1, 4 },
            { 3, 7 }
    };

    static int[][] C = new int[2][2];
    static int[][] D = new int[2][2];

    public static void main(String[] args) throws Exception {

        long t1 = System.nanoTime();

        // Normal Multiplication
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        long t2 = System.nanoTime();

        // Multithreaded Multiplication
        long t3 = System.nanoTime();

        Thread[] th = new Thread[2];

        for (int i = 0; i < 2; i++) {
            final int row = i;

            th[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        D[row][j] += A[row][k] * B[k][j];
                    }
                }
            });

            th[i].start();
        }

        for (Thread t : th) {
            t.join();
        }

        long t4 = System.nanoTime();

        // Display Results
        System.out.println("Normal Multiplication:");
        for (int[] r : C) {
            for (int x : r)
                System.out.print(x + " ");
            System.out.println();
        }

        System.out.println("Multithreaded Multiplication:");
        for (int[] r : D) {
            for (int x : r)
                System.out.print(x + " ");
            System.out.println();
        }

        // Performance Comparison
        System.out.println("Normal Time: " + (t2 - t1) + " ns");
        System.out.println("Multithreaded Time: " + (t4 - t3) + " ns");
    }
}
