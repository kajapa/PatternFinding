package Shapes;



import java.awt.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Utilities.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ScatterPlotExample extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public ScatterPlotExample(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "PatterFinding",
                "X-Axis", "Y-Axis", dataset);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        Circle nn = new Circle(100, 100, 100, 500, 500);
        Circle n = new Circle(100, 100, 100, 500, 500);
        System.out.println("W " + Frame.WIDTH + " H " + Frame.HEIGHT);
        XYSeries series1 = new XYSeries("No noise");


        for (Utilities.Point p : nn.ReturnPoints(false)) {
            //System.out.println(p.x + " " + p.y);
            series1.add(p.x, p.y);
        }


        XYSeries series2 = new XYSeries("Noise");
        for (Point p : n.ReturnPoints(true)) {
            // System.out.println(p.x + " " + p.y);
            series2.add(p.x, p.y);
        }


        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }
    double CheckDistance(Point a,Point b)
    {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
            example.setSize(800, 800);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }

}