package Shapes;

import Utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Circle extends JPanel {
    private int a;
    private int b;
    private int r;
    private int rq;
    private int aq;
    private int bq;
    private int step = 15;
    private int width;
    private int height;
    private int raynoise =20;
    private int thetanoise=15;
    private List<Point> points = new ArrayList<Point>();
    static Random ran= new Random();
    List<Double> gauss= new ArrayList<Double>();


    public Circle(int a, int b, int r, int width, int height) {
        super.setDoubleBuffered(true);
        this.a = a;
        this.b = b;
        this.r = r;
        this.aq = (int) Math.pow(a, 2);
        this.bq = (int) Math.pow(b, 2);
        this.rq = (int) Math.pow(r, 2);
        this.width = width;
        this.height = height;

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

                for (int a=0;a<300;++a)
                {
                    gauss.add(ran.nextGaussian()*raynoise+r);
                }

        int theta;
        double px;
        double py; double r2=r;


        for (theta = 0; theta <= 360; theta += 10) {
            double rand =  (Math.random() * (raynoise - 1)) + 1;

            r2=r-rand;
            System.out.println("Ray: " + r2);
            px =  (a + r2 * Math.cos(theta));
            py =  (b + r2* Math.sin(theta));
            Ellipse2D dot = new Ellipse2D.Double(px - 1, py - 1, 2, 2);

            g2d.setColor(Color.BLUE);

            g2d.fill(dot);
            //points.add(new Point(px, py));
            thetanoise= (int) (Math.random() * (15 - 1)) + 1;
            //g2d.fillRect(px, py, 1, 1);
            Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
            System.out.println("Average "+average);
            System.out.println("Deviation "+sd(gauss));
        }


    }

    public static double sd (List<Double> table)
    {
        // Step 1:

        double mean =  table.stream().mapToDouble(val -> val).average().orElse(0.0);
        double temp = 0;

        for (int i = 0; i < table.size(); i++)
        {
            double val = table.get(i);

            // Step 2:
            double squrDiffToMean = Math.pow(val - mean, 2);

            // Step 3:
            temp += squrDiffToMean;
        }

        // Step 4:
        double meanOfDiffs = (double) temp / (double) (table.size());

        // Step 5:
        return Math.sqrt(meanOfDiffs);
    }


}
