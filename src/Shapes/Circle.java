package Shapes;

import Utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Circle {
    private int a;
    private int b;
    private int r;
    private int rq;
    private int aq;
    private int bq;
    private int step = 15;
    private int width;
    private int height;
    private double raynoise = 20;
    private double thetanoise = 15;
    public List<Point> points = new ArrayList<Point>();
    static Random ran = new Random();
    List<Double> gauss = new ArrayList<Double>();


    public Circle(int a, int b, int r, int width, int height) {

        this.a = a;
        this.b = b;
        this.r = r;
        this.aq = (int) Math.pow(a, 2);
        this.bq = (int) Math.pow(b, 2);
        this.rq = (int) Math.pow(r, 2);
        this.width = width;
        this.height = height;

    }


    public List<Point> ReturnPoints(boolean change) {
        double theta;
        double px;
        double py;
        if (change) {


            double r2;

            double t2 = 10;
            for (theta = 0; theta <= 360; theta += t2) {


                r2 = ran.nextGaussian() * raynoise + r;
                System.out.println("Ray2: " + r2);
                px = (a + r2 * Math.cos(theta));
                py = (b + r2 * Math.sin(theta));
                t2 = ran.nextGaussian() * thetanoise + 15;
                points.add(new Point(px, py));

                //g2d.fillRect(px, py, 1, 1);
           /* Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
            System.out.println("Average "+average);
            System.out.println("Deviation "+sd(gauss));*/
            }
            return points;
        } else {
            // double r2=r;


            for (theta = 0; theta <= 360; theta += 10) {
                //double rand =  (Math.random() * (raynoise - 1)) + 1;

                // r2=r-rand;
                System.out.println("Ray: " + r);
                px = (a + r * Math.cos(theta));
                py = (b + r * Math.sin(theta));

                points.add(new Point(px, py));
               /*thetanoise= (int) (Math.random() * (15 - 1)) + 1;
               //g2d.fillRect(px, py, 1, 1);
               Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
               System.out.println("Average "+average);
               System.out.println("Deviation "+sd(gauss));*/
            }
            return points;
        }

    }

    public static double sd(List<Double> table) {
        // Step 1:

        double mean = table.stream().mapToDouble(val -> val).average().orElse(0.0);
        double temp = 0;

        for (int i = 0; i < table.size(); i++) {
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
