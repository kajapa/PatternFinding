package Utilities;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void Print() {
        System.out.println(x + " " + y);
    }
}
