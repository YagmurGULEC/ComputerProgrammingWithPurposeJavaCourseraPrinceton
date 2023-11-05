public class Clock {

    private static final int MAXMIN = 60;
    private static final int MAXHOURS = 24;
    private int hour, min;

    private static boolean checkValue(int h, int m) {
        if ((h < 0 || h > MAXHOURS - 1) || (m < 0 || m > MAXMIN - 1))
            return false;
        else
            return true;
    }

    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m) {
        if (!checkValue(h, m)) {
            throw new IllegalArgumentException(
                    "wrong value");
        } else {

            hour = h;
            min = m;
        }

    }

    // Creates a clock whose initial time is specified as a string, using the format
    // HH:MM.
    public Clock(String s) {
        if (s.contains(":") && s.length() == 5) {
            int h = Integer.parseInt(s.substring(0, 2));
            int m = Integer.parseInt(s.substring(3, 5));
            if (!checkValue(h, m)) {
                throw new IllegalArgumentException(
                        "wrong value");
            } else {
                hour = h;
                min = m;
            }

        } else

        {
            throw new IllegalArgumentException(
                    "wrong value");
        }
    }

    // Returns a string representation of this clock, using the format HH:MM.
    public String toString() {
        String h = Integer.toString(hour);
        String m = Integer.toString(min);
        if (h.length() >= 2 && m.length() >= 2)
            return h + ":" + m;
        else {
            if (h.length() < 2)
                h = "0" + h;

            if (m.length() < 2)
                m = "0" + m;
            return h + ":" + m;
        }

    }

    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock that) {
        if (hour == that.hour)
            return (min < that.min);
        else
            return (hour < that.hour);

    }

    // Adds 1 minute to the time on this clock.
    public void tic() {
        min++;
        hour += min / MAXMIN;
        hour = hour % MAXHOURS;
        min = min % MAXMIN;

    }

    // Adds Δ minutes to the time on this clock.
    public void toc(int delta) {
        if (delta < 0)
            throw new IllegalArgumentException(
                    "wrong value");
        min += delta;
        hour += min / MAXMIN;
        hour = hour % MAXHOURS;
        min = min % MAXMIN;

    }

    // Test client (see below).
    public static void main(String[] args) {

        Clock c = new Clock(1, 55);
        Clock c2 = new Clock(23, 55);
        c.toc(60);
        c.tic();
        StdOut.println(c.toString());
        StdOut.println(c2.toString());
        StdOut.println(c.isEarlierThan(c2));
    }
}