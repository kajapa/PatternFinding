package Shapes;

import Utilities.Point;

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
    private double raynoise = 5;
    private double thetanoise = 5;
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
        double r2;
        double t2 ;

        if (change) {
            points.clear();
            for (theta = 0; theta <= 360; theta += 10) {
                r2 = ran.nextGaussian() * raynoise + r;
                t2 = ran.nextGaussian() * thetanoise + theta;
                // r2=r-rand;
                //System.out.println("Ray noise: " + r2);

                px = (a + r2 * Math.cos(t2));
                py = (b + r2 * Math.sin(t2));

                points.add(new Point(px, py));
               /*thetanoise= (int) (Math.random() * (15 - 1)) + 1;
               //g2d.fillRect(px, py, 1, 1);
               Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
               System.out.println("Average "+average);
               System.out.println("Deviation "+sd(gauss));*/
            }






            //g2d.fillRect(px, py, 1, 1);
           /* Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
            System.out.println("Average "+average);
            System.out.println("Deviation "+sd(gauss));*/
            System.out.println("Noise list: "+points.size());
            return points;
        } else {
            points.clear();
            System.out.println("Ray: " + r);
            for (theta = 0; theta <= 360; theta += 10) {
                //double rand =  (Math.random() * (raynoise - 1)) + 1;

                // r2=r-rand;

                px = (a + r * Math.cos(theta));
                py = (b + r * Math.sin(theta));

                points.add(new Point(px, py));
               /*thetanoise= (int) (Math.random() * (15 - 1)) + 1;
               //g2d.fillRect(px, py, 1, 1);
               Double average = gauss.stream().mapToDouble(val -> val).average().orElse(0.0);
               System.out.println("Average "+average);
               System.out.println("Deviation "+sd(gauss));*/
            }

            System.out.println("No Noise list: "+points.size());
            CreateMatrix(points);
            return points;
        }

    }
    public double[][] CreateMatrix(List<Point> in)
    {
        double[][] result= new double[in.size()][3];
        for(int i=0;i<in.size();++i)
        {
            result[i][0]= points.get(i).x;
            result[i][1]= points.get(i).y;
            result[i][2]= 1;
        }

        return  result;
    }
    public double[][] GetTranMatrix(Point p,Point s,double ang)
    {
        double[][] result= new double[3][3];
        result[0][0]=s.x*Math.cos(ang);
        result[0][1]=-Math.sin(ang);
        result[0][2]=p.x;
        result[1][0]=s.y*Math.sin(ang);
        result[1][1]=Math.cos(ang);
        result[1][2]=p.y;
        result[2][0]=0;
        result[2][1]=0;
        result[2][2]=1;
        return result;
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
