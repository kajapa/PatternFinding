package Shapes;

import Utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private int raynoise = 10;
    private int thetanoise=15;
    private List<Point> points = new ArrayList<Point>();


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


        int theta;
        int px;
        int py;
        for (theta = 0; theta <= 360; theta += thetanoise) {
            int rand = (int) (Math.random() * (raynoise - 1)) + 1;
            System.out.println("Ray: " + rand);
            r=r-rand;
            px = (int) (a + r * Math.cos(theta));
            py = (int) (b + r * Math.sin(theta));


            g2d.setColor(Color.BLUE);
            g2d.fill3DRect(px, py, 5, 5, true);
            points.add(new Point(px, py));
            thetanoise= (int) (Math.random() * (15 - 1)) + 1;
            //g2d.fillRect(px, py, 1, 1);
        }


    }


}
