package Shapes;

import java.awt.*;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Utilities.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawChart extends JFrame {
    public DrawChart() {
        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {
        Circle nn = new Circle(100, 100, 100, 500, 500);
        Circle n = new Circle(100, 100, 100, 500, 500);
        System.out.println("W " + Frame.WIDTH + " H " + Frame.HEIGHT);
        XYSeries series1 = new XYSeries("No noise");
        List<Point> nonoise = nn.ReturnPoints(false);
        List<Point> noise = n.ReturnPoints(true);
        for (Point p : nonoise) {
            //System.out.println(p.x + " " + p.y);
            series1.add(p.x, p.y);
        }


        XYSeries series2 = new XYSeries("Noise");
        for (Point p : n.ReturnPoints(true)) {
           // System.out.println(p.x + " " + p.y);
            series2.add(p.x, p.y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        System.out.println("Distance:"+CheckDistance(nonoise.get(0),noise.get(0)));
        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "PatternFinding",
                "X",
                "Y",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(0));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(0));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("PatternFinding",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            DrawChart ex = new DrawChart();
            ex.setVisible(true);
        });
    }
    double CheckDistance(Point a,Point b)
    {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }
}
