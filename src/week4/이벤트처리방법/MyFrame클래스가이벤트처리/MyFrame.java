package week4.이벤트처리방법.MyFrame클래스가이벤트처리;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JButton button;
    private JLabel label;

    public MyFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        button = new JButton("버튼을 누르시오");
        label = new JLabel("아직 버튼이 눌러지지 않았습니다");
        button.addActionListener(this);
        panel.add(button);
        panel.add(label);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            label.setText("마침내 버튼이 눌려졌습니다.");
        }
    }

    public static void main(String[] args) {
        MyFrame t = new MyFrame();
    }
}
