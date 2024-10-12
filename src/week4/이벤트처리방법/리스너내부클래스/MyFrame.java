package week4.이벤트처리방법.리스너내부클래스;

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
        button.addActionListener(new MyListener());
        panel.add(button);
        panel.add(label);
        add(panel);
        setVisible(true);
    }

    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            button.setText("마침내 버튼이 눌러졌습니다.");
        }
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }
}
