package week3.온도변환기;

import javax.swing.*;

public class Mylab extends JFrame {

    public Mylab() {
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mylab");

        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("화씨 온도");
        JLabel label2 = new JLabel("섭씨 온도");
        JTextField field1 = new JTextField(15);
        JTextField field2 = new JTextField(15);
        JButton button = new JButton("변환");

        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(button);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Mylab f = new Mylab();
    }
}
