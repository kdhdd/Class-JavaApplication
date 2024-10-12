package week4.이벤트처리방법.람다식;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    private JButton button;
    private JLabel label;

    public MyFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        button = new JButton("버튼을 누르시오");
        label = new JLabel("아직 버튼이 눌러지지 않았습니다");
        button.addActionListener( (e) -> {
            label.setText("마침내 버튼이 눌려졌습니다.");
        });
        panel.add(button);
        panel.add(label);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }
}
