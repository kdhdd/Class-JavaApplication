package week5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ex extends JFrame implements ActionListener {
    private JTextField txt;
    private JPanel panel;
    private Stack<Integer> valueStack = new Stack<>();
    private Stack<String> operatorStack = new Stack<>();
    private boolean newCalculator = false;

    public ex() {
        txt = new JTextField(20);
        add(txt, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        add(panel, BorderLayout.CENTER);

        String[] firstRow = {"1", "2", "3", "+"};
        for (String s : firstRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] secondRow = {"4", "5", "6", "-"};
        for (String s : secondRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] thirdRow = {"7", "8", "9", "x"};
        for (String s : thirdRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] fourthRow = {"/", "0", "="};
        for (String s : fourthRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("=")) {
            while (!operatorStack.isEmpty()) {
                calculate();
            }
            txt.setText("" + valueStack.pop());
            newCalculator = true;

        } else if (actionCommand.equals("+") || actionCommand.equals("-") ||
                actionCommand.equals("x") || actionCommand.equals("/")) {
            if (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(actionCommand)) {
                calculate();
            }
            operatorStack.push(actionCommand);
            txt.setText(txt.getText() + " " + actionCommand + " ");
            newCalculator = false;

        } else {
            if (newCalculator) {
                txt.setText("");
                valueStack.clear();
                operatorStack.clear();
                newCalculator = false;
            }
            txt.setText(txt.getText() + actionCommand);
            valueStack.push(Integer.parseInt(actionCommand));
        }
    }

    // 연산자 우선순위 설정 메서드
    private int precedence(String operator) {
        switch (operator) {
            case "x":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    // 계산을 수행하는 메서드
    private void calculate() {
        if (valueStack.size() < 2 || operatorStack.isEmpty()) return;

        int b = valueStack.pop();
        int a = valueStack.pop();
        String operator = operatorStack.pop();

        switch (operator) {
            case "+":
                valueStack.push(a + b);
                break;
            case "-":
                valueStack.push(a - b);
                break;
            case "x":
                valueStack.push(a * b);
                break;
            case "/":
                valueStack.push(a / b);
                break;
        }
    }

    public static void main(String[] args) {
        new ex();
    }
}
