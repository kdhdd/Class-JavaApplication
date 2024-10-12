package week3;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        setSize(300, 200);
        setLocation(screenSize.width / 2, screenSize.height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("week3.MyFrame");

        Image img = kit.getImage("back.jpg");
        setIconImage(img);

        setLayout(new FlowLayout());
        JButton button1 = new JButton("버튼1");
        JButton button2 = new JButton("버튼2");
        this.add(button1);
        this.add(button2);

        setVisible(true);
    }
}
