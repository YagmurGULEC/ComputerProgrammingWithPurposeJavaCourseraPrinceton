public class Inversions {

    // Return the number of inversions in the permutation a[].
    public static long count(int[] a) {
        long count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j])
                    count++;
            }
        }
        return count;
    }
    // Return a permutation of length n with exactly k inversions.

    public static int[] generate(int n, long k) {
        int a[] = new int[n];

        long D = 1 + 8 * k;
        int root = (int) ((1 + Math.sqrt(D)) / 2);
        int remaining = (int) k - (root * (root - 1) / 2);
        int index = a.length - 1 - remaining;
        int last = n - 1;

        int old = -1;
        for (int i = 0; i < n; i++) {
            if (k == 0)
                a[i] = i;
            else if (k == (long) (n * (n - 1) / 2))
                a[a.length - i - 1] = i;
            else {

                if (root - i - 1 >= 0) {
                    a[i] = root - i - 1;

                }
                if (root - i - 1 < 0)
                    a[i] = i;
                if (i == index) {

                    old = a[i];
                    a[i] = last;

                }
                if (index <= root - 1) {
                    if (old >= 0)

                        if ((old - (i - index) + 1 >= 0) && (i != index))
                            a[i] = old - (i - index) + 1;
                        else if ((old - (i - index) + 1 < 0) && (i != index))
                            a[i] = a[i] - 1;
                }
                if (index > root - 1) {
                    if (old >= 0)
                        if ((old + (i - index) <= n - 1) && (i != index))
                            a[i] = old + (i - index) - 1;

                }

            }
        }
        return a;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);
        int[] a = generate(n, k);
        long number = count(a);
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();

    }
}