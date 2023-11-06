import java.util.Arrays;

public class BarChartRacer {
    public static void main(String[] args) {
        String fileName = args[0];
        int numberOfBars = Integer.parseInt(args[1]);
        In fileRead = new In(fileName);
        String t = fileRead.readLine();
        String xAxis = fileRead.readLine();
        String source = fileRead.readLine();
        fileRead.readLine();
        BarChart bar = new BarChart(t, xAxis, source);

        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        while (fileRead.hasNextLine()) {
            int n = Integer.parseInt(fileRead.readLine());

            Bar[] b = new Bar[n];
            String year = "";
            for (int i = 0; i < n; i++) {
                String text = fileRead.readLine();
                // StdOut.println(text);
                String[] data = text.split(",");
                year = data[0];
                String name = data[1];
                int v = Integer.parseInt(data[3]);
                String c = data[4];
                b[i] = new Bar(name, v, c);

            }
            Arrays.sort(b);
            bar.setCaption(year);
            for (int i = b.length - 1; i > b.length - 1 - numberOfBars; i--) {
                bar.add(b[i].getName(), b[i].getValue(), b[i].getCategory());

            }

            StdDraw.clear();
            bar.draw();
            StdDraw.show();
            StdDraw.pause(100);
            bar.reset();

            fileRead.readLine();
        }
        bar.reset();
    }
}