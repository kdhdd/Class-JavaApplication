package week3.파운드를킬로그램으로;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LbToKgConverter extends JFrame {

    private JTextField lbInput;
    private JTextField kgOutput;

    public LbToKgConverter() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        setSize(300, 150);
        setLocation(screenSize.width / 2, screenSize.height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("무게 변환 계산기");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel lbPanel = new JPanel();
        JPanel kgPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        JLabel lbLabel = new JLabel("파운드 값");
        lbInput = new JTextField(10);

        JLabel kgLabel = new JLabel("킬로그램 값");
        kgOutput = new JTextField(10);

        JButton converterButton = new JButton("Convert");

        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertLbToKg();
            }
        });


        lbPanel.add(lbLabel);
        lbPanel.add(lbInput);
        kgPanel.add(kgLabel);
        kgPanel.add(kgOutput);
        buttonPanel.add(converterButton);

        panel.add(lbPanel);
        panel.add(kgPanel);
        panel.add(buttonPanel);

        add(panel);

        setVisible(true);
    }

    private void convertLbToKg() {
        double lb = Double.parseDouble(lbInput.getText());
        double kg = lb * 0.453592;

        kgOutput.setText(String.format("%.3f", kg));
    }

    public static void main(String[] args) {
        LbToKgConverter f = new LbToKgConverter();
    }
}
