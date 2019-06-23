package Main;


import Shapes.Circle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {
    int width = 1000;
    int height = 1000;

    public Main()throws IOException
    {

        super.setSize(width, height);

        super.setLayout(new BorderLayout());

        super.setTitle("PatternFinding");

        super.setLocation(0, 0);
        super.setResizable(false);
        Circle c= new Circle(width/2,height/2,100,width,height);
        //super.add(trace);

        super.add(c);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
       new Main();







    }
}
