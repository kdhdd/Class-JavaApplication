package week4.화면에난수표시하기;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    JPanel p = new JPanel();
    JLabel[] labels = new JLabel[30];

    public MyFrame() {
        p.setLayout(null);
        p.setBackground(Color.YELLOW);
        for (int i = 0; i < 30; i++) {
            labels[i] = new JLabel("" + i);
            int x = (int) (500 * Math.random());
            int y = (int) (300 * Math.random());
            labels[i].setForeground(Color.MAGENTA);
            labels[i].setLocation(x, y);
            labels[i].setSize(20, 20);
            p.add(labels[i]);
        }
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(p);
        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }
}
