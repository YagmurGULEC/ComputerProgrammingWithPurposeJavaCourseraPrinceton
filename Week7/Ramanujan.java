public class Ramanujan {

    // Is n a Ramanujan number?
    public static boolean isRamanujan(long n) {
        int cnt = 0;
        int b = 0;
        for (int i = 1; i < Math.cbrt(n); i++) {
            double a = Math.cbrt(n - Math.pow(i, 3));
            if (a == (int) a) {

                if (cnt == 0) {
                    b = (int) a;
                    cnt++;

                } else if (i != b)
                    cnt++;
                if (cnt == 2)
                    return true;
            }

        }
        return false;
    }

    // Takes a long integer command-line arguments n and prints true if
    // n is a Ramanujan number, and false otherwise.
    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        StdOut.println(isRamanujan(n));
    }
}