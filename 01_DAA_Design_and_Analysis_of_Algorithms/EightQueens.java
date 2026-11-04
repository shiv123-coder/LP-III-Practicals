
public class EightQueens {
    static int[][] board = new int[8][8];

    static boolean safe(int r, int c) {
        for (int i = 0; i < r; i++)
            if (board[i][c] == 1)
                return false;

        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (int i = r - 1, j = c + 1; i >= 0 && j < 8; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solve(int r) {
        if (r == 8)
            return true;

        for (int c = 0; c < 8; c++) {
            if (safe(r, c)) {
                board[r][c] = 1;

                if (solve(r + 1))
                    return true;

                board[r][c] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        board[0][0] = 1;

        if (solve(1)) {
            System.out.println("8 Queens Solution:");

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }
        } else {
            System.out.println("No solution exists.");
        }
    }
}
