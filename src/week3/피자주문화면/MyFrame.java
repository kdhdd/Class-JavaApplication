package week3.피자주문화면;

import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("week3.MyFrame");

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        JLabel label1 = new JLabel("자바 피자에 오신 것을 환영합니다." +
                "피자의 종류를 선택하시오.");
        panel1.add(label1);

        JButton button1 = new JButton("콤보피자");
        JButton button2 = new JButton("포테이토피자");
        JButton button3 = new JButton("불고기피자");
        JLabel label2 = new JLabel("개수");
        JTextField field = new JTextField(10);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(label2);
        panel2.add(field);

        panel.add(panel1);
        panel.add(panel2);

        this.add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }
}
