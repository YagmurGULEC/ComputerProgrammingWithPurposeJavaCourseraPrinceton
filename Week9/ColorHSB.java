public class ColorHSB {
    private final int hue, saturation, brightness;
    private static final int MAX = 360;

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {
        if ((h <= MAX - 1 && h >= 0) || (s <= 100 && s >= 0) || (b <= 100 && b >= 0)) {
            hue = h;
            saturation = s;
            brightness = b;
        } else {
            throw new IllegalArgumentException(
                    "hue should be between 0 and 359,saturation and brightness should be between 0 and 100");
        }

    }

    private static int squareToInt(int a) {
        return (int) Math.pow(a, 2);
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return "(" + hue + ", " + saturation + ", " + brightness + ")";
    }

    // Is this color a shade of gray?
    public boolean isGrayscale() {
        return (saturation == 0 || brightness == 0);
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that) {
        if (that == null)
            throw new IllegalArgumentException(
                    "ColorHSB not defined");
        else {
            int diffH = hue - that.hue;
            int diffS = saturation - that.saturation;
            int diffB = brightness - that.brightness;
            int first = Math.min(squareToInt(diffH), squareToInt(MAX - Math.abs(diffH)));
            int second = squareToInt(diffS);
            int third = squareToInt(diffB);
            return (first + second + third);
        }

    }

    // Sample client (see below).
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB c = new ColorHSB(h, s, b);
        ColorHSB closest = c;
        int minDist = MAX * MAX * MAX;
        String closestColorName = "";
        // StdOut.println(c.distanceSquaredTo(newC));
        while (!StdIn.isEmpty()) {
            String n = StdIn.readString();
            int hueRead = StdIn.readInt();
            int sRead = StdIn.readInt();
            int bRead = StdIn.readInt();
            ColorHSB newC = new ColorHSB(hueRead, sRead, bRead);
            int newDistance = c.distanceSquaredTo(newC);
            if (newDistance < minDist) {
                minDist = newDistance;
                closestColorName = n;
                closest = newC;
            }

        }
        StdOut.println(closestColorName + " " + closest.toString());
    }

}