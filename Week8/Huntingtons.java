public class Huntingtons {

    // Returns the maximum number of consecutive repeats of CAG in the DNA string.
    public static int maxRepeats(String dna) {
        String x;
        int max = 0, count = 0;
        int i = 0;

        while (i < dna.length() - 2) {
            // StdOut.println(dna.substring(i, i + 3));
            x = dna.substring(i, i + 3);
            // StdOut.println(x);
            if (x.equals("CAG")) {
                count++;
                i += 3;
                if (count > max)
                    max = count;
            } else {
                i++;
                count = 0;
            }

        }
        return max;

    }

    // Returns a copy of s, with all whitespace (spaces, tabs, and newlines)
    // removed.
    public static String removeWhitespace(String s) {

        String t1 = s.replaceAll("\n", "");
        String t2 = t1.replaceAll("\t", "");
        String t3 = t2.replaceAll(" ", "");
        return t3;
    }

    // Returns one of these diagnoses corresponding to the maximum number of
    // repeats:
    // "not human", "normal", "high risk", or "Huntington's".
    public static String diagnose(int maxRepeats) {

        if (maxRepeats <= 9 || maxRepeats > 180)
            return "not human";
        else if (maxRepeats <= 35)
            return "normal";
        else if (maxRepeats <= 39)
            return "high risk";
        else
            return "Huntington's";
    }

    // Sample client (see below).
    public static void main(String[] args) {
        String fileName = args[0];
        In fileRead = new In(fileName);
        String s = fileRead.readAll();
        String t = removeWhitespace(s);
        int count = maxRepeats(t);
        StdOut.println("max repeats = " + count);
        StdOut.println(diagnose(count));
    }

}