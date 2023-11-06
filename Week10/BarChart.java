import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;

public class BarChart {
    private final String title, xAxisLabel, dataSource;
    private String caption;
    private ArrayList<Bar> bars;
    private int numberOfBars;
    private static Color[] colors = getSomeColor(30);

    public static Color[] getSomeColor(
            int numberofColors) {
        Color newColors[] = new Color[numberofColors];
        for (int i = 0; i < numberofColors; i++) {
            Color nc = new Color((int) (Math.random() * 0x1000000));
            newColors[i] = nc;
        }
        return newColors;
    }

    // Creates a bar chart with the given title, x-axis label, and data source.
    public BarChart(String title, String xAxisLabel, String dataSource) {
        if (title == null)
            throw new IllegalArgumentException();
        if (xAxisLabel == null)
            throw new IllegalArgumentException(
                    "name is null");
        if (dataSource == null)
            throw new IllegalArgumentException(
                    "name is null");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        reset();

    }

    // Sets the caption of this bar chart.
    public void setCaption(String caption) {
        if (caption == null)
            throw new IllegalArgumentException();
        this.caption = caption;
    }

    // Adds a bar (name, value, category) to this bar chart.
    public void add(String name, int value, String category) {
        Bar newbar = new Bar(name, value, category);
        bars.add(newbar);

    }

    // Remove all of the bars from this bar chart.
    public void reset() {
        bars = new ArrayList<Bar>();
    }

    // Draws this bar chart to standard draw.
    public void draw() {

        Bar[] barArrays = new Bar[bars.size()];
        double maxValue = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < bars.size(); i++) {

            barArrays[i] = bars.get(i);
            if (barArrays[i].getValue() > maxValue) {
                maxValue = barArrays[i].getValue();
            }
        }
        // Arrays.sort(barArrays);
        int numberOfBars = barArrays.length;

        StdDraw.setXscale(-0.01 * maxValue, 1.2 * maxValue);
        StdDraw.setYscale(-0.01 * numberOfBars, numberOfBars * 1.25);
        // draw title
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * maxValue, numberOfBars * 1.2, title);
        // draw x-axis label
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
        StdDraw.textLeft(0, numberOfBars * 1.10, xAxisLabel);

        double barWidth = 0.01;
        for (int i = 0; i < numberOfBars; i++) {
            // StdOut.println(bars.get(i));
            int v = barArrays[i].getValue();
            StdDraw.setPenColor(colors[i]);
            StdDraw.filledRectangle(0.5 * v, numberOfBars - i - 0.5, 0.5 * v, 0.4);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
            StdDraw.text(v * 0.9, numberOfBars - i - 0.5, barArrays[i].getName());
            StdDraw.text(v * 1.1, numberOfBars - i - 0.5, String.format("%d", v));
            // StdOut.println(barArrays[i].getValue());
        }

        StdDraw.filledRectangle(0.1, 0.4, 0.5, 0.1);
    }

    public static void main(String[] args) {
        // create the bar chart
        String title = "The 10 most populous cities";
        String xAxis = "Population (thousands)";
        String source = "Source: United Nations";
        BarChart chart = new BarChart(title, xAxis, source);
        chart.setCaption("2018");
        // add the bars to the bar chart
        chart.add("Tokyo", 38194, "East Asia");
        chart.add("Delhi", 27890, "South Asia");
        chart.add("Shanghai", 25779, "East Asia");
        chart.add("Beijing", 22674, "East Asia");
        chart.add("Mumbai", 22120, "South India");
        chart.add("São Paulo", 21698, "Latin America");
        chart.add("Mexico City", 21520, "Latin America");
        chart.add("Osaka", 20409, "East Asia");
        chart.add("Cairo", 19850, "Middle East");
        chart.add("Dhaka", 19633, "South Asia");
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        chart.draw();
        StdDraw.show();

    }
}