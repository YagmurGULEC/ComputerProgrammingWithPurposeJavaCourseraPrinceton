import java.awt.Color;

public class KernelFilter {

    private static int clamp(double value) {
        int v = (int) Math.round(value);
        return Math.max(0, Math.min(255, v));
    }

    private static Picture convolution(Picture original, double[][] filter) {
        int filterSize = filter.length;
        int dx = (filterSize / 2);
        int width = original.width();
        int height = original.height();
        Picture newPic = new Picture(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double reds = 0;
                double greens = 0;
                double blues = 0;
                for (int i = -dx; i <= dx; i++) {
                    for (int j = -dx; j <= dx; j++) {

                        Color c = original.get((width + col + i) % width, (height + row + j) %
                                height);
                        reds += c.getRed() * filter[i + dx][j + dx];
                        greens += c.getGreen() * filter[i + dx][j + dx];
                        blues += c.getBlue() * filter[i + dx][j + dx];

                    }
                    int r = clamp(reds);
                    int g = clamp(greens);
                    int b = clamp(blues);
                    Color nc = new Color(r, g, b);
                    newPic.set(col, row, nc);
                }

            }

        }
        return newPic;

    }

    // Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture) {

        int width = picture.width();
        int height = picture.height();
        Picture newPic = new Picture(width, height);
        for (int col = 0; col < width; col++)

            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                newPic.set(col, row, color);
            }

        return newPic;
    }

    // Returns a new picture that applies a Gaussian blur filter to the given
    // picture.
    public static Picture gaussian(Picture picture) {
        double[][] filter = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };

        for (int i = 0; i < filter.length; i++)
            for (int j = 0; j < filter[0].length; j++)
                filter[i][j] = filter[i][j] / 16.0;
        return convolution(picture, filter);
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        double[][] filter = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
        return convolution(picture, filter);
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        double[][] filter = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
        return convolution(picture, filter);
    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        double[][] filter = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
        return convolution(picture, filter);
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        double[][] filter = new double[9][9];
        for (int i = 0; i < filter.length; i++)
            for (int j = 0; j < filter[0].length; j++) {
                if (i == j)
                    filter[i][j] = 1 / 9.0;
            }
        return convolution(picture, filter);

    }

    // Test client (ungraded).
    public static void main(String[] args) {

        Picture source = new Picture(args[0]);
        Picture id = identity(source);
        // id.show();
        Picture gauss = gaussian(source);
        gauss.show();
        Picture sharp = sharpen(source);
        sharp.show();
        Picture e = emboss(source);
        e.show();
        Picture l = laplacian(source);
        l.show();
        Picture motion = motionBlur(source);
        motion.show();
    }

}