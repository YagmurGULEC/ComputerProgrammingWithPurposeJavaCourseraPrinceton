public class MaximumSquareSubmatrix {
    public static int size(int[][] a) {
        int largest = 0;

        int[][] dp = new int[a.length + 1][a.length + 1];

        for (int i = 1; i < a.length + 1; i++) {
            for (int j = 1; j < a.length + 1; j++) {
                if (a[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > largest)
                        largest = dp[i][j];
                }
            }
        }
        // for (int i = 0; i < a.length; i++) {
        // for (int j = 0; j < a.length; j++) {
        // StdOut.print(dp[i][j] + " ");
        // }
        // StdOut.println();
        // }

        return largest;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] a = new int[n][n];
        while (!StdIn.isEmpty()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = StdIn.readInt();
                }
            }
        }

        int l = size(a);
        StdOut.println(l);
    }
}