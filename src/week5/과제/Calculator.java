package week5.과제;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator extends JFrame implements ActionListener {
    private JTextField txt;
    private JTextField errorTxt;
    private JPanel panel;
    private Stack<String> valueStack = new Stack<>(); // 숫자를 보관할 스택
    private Stack<String> operatorStack = new Stack<>(); // 연산자를 보관할 스택
    private boolean newCalculator = false; // 연산 이후 숫자를 입력하면 계산기를 초기화하기 위한 변수

    public Calculator() {
        txt = new JTextField(20);
        add(txt, BorderLayout.NORTH);

        errorTxt = new JTextField(20);
        add(errorTxt, BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4)); // 5행 4열
        add(panel, BorderLayout.CENTER);

        String[] firstRow = {"1", "2", "3", "+"};   // 1행에 1, 2, 3, +
        for (String s : firstRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] secondRow = {"4", "5", "6", "-"};  // 2행에 4, 5, 6 -
        for (String s : secondRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] thirdRow = {"7", "8", "9", "x"};   // 3행에 7, 8, 9, x
        for (String s : thirdRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] fourthRow = {"/", "0", "=", "C"};  // 4행에 /, 0, = , C
        for (String s : fourthRow) {
            JButton btn = new JButton(s);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(100, 100));
            panel.add(btn);
        }

        String[] fifthRow = {"(", ")"};     // 5행에 (, )
        for (String s : fifthRow) {
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
        errorTxt.setText("");
        if (actionCommand.equals("=")) {    // =이 클릭 되면
            // 닫힌 괄호와 열린 괄호의 수가 일치하지 않는다면 보류
            // 3 + 9 x 처럼 연산자 수와 숫자의 수가 같거나 많으면 보류
            // 숫자만 있거나 없을 경우 보류
            if (operatorStack.contains("(") || operatorStack.size() >= valueStack.size() || valueStack.size() <= 1) {
                errorTxt.setText("올바르지 않은 수식입니다.");
                return;
            }

            while (!operatorStack.isEmpty()) {  // 연산 수행.
                calculate();
            }
            txt.setText(valueStack.peek()); // 결과값 화면에 출력
            newCalculator = true;   // 이후에 숫자가 입력될 것을 대비하여 true로 설정
        } else if (actionCommand.equals("+") || actionCommand.equals("-") ||    // 연산자가 클릭 되면
                actionCommand.equals("x") || actionCommand.equals("/")) {
            // 연산자가 먼저 클릭된다면 보류
            if (valueStack.isEmpty()) {
                errorTxt.setText("올바르지 않은 수식입니다.");
                return;
            }

            // 연산자 뒤에 연산자가 오는 것을 방지
            if (!Character.isDigit(txt.getText().charAt(txt.getText().length() - 1)) &&
            txt.getText().charAt(txt.getText().length() - 1) != ')') {
                errorTxt.setText("올바르지 않은 수식입니다.");
                return;
            }

            // 연산자 우선순위에 따라 연산 수행. 3 x 4 + 가 입력 될 경우 3 x 4를 먼저 연산하여 값을 스택에 저장해둠
            if (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(actionCommand)) {
                calculate();
            }
            operatorStack.push(actionCommand); // 연산자 스택에 추가
            txt.setText(txt.getText() + " " + actionCommand + " "); // 연산자를 띄어쓰기하여 화면에 출력
            newCalculator = false;  // 연산자가 입력될 경우 연산을 이어가야하기 때문에 false로 설정
        } else if (actionCommand.equals("C")) { // C가 클릭되면
            txt.setText("");    // 출력창 초기화
            // 스택 초기화
            valueStack.clear();
            operatorStack.clear();
            // 새로운 연산을 위해 false로 설정
            newCalculator = false;
        } else if (actionCommand.equals("(")) { // 열린 괄호가 클릭되면
            // 열린 괄호 클릭 이전에 숫자가 있다면. 9(2 + 3)
            if (!valueStack.isEmpty() && Character.isDigit(txt.getText().charAt(txt.getText().length() - 1))) {
                operatorStack.push("x");    // x 연산자 스택 추가
                txt.setText(txt.getText() + " x "); // 화면에 9 x (2 + 3)으로 바꾸어 출력
            }

            operatorStack.push(actionCommand);  // 열린 괄호를 연산자 스택에 추가
            txt.setText(txt.getText() + actionCommand); // 화면에 출력
        } else if (actionCommand.equals(")")) { // 닫힌 괄호가 클릭되면
            // 열린 괄호와 만날 때까지 괄호 안에 있는 수식을 연산
            while (!operatorStack.isEmpty() && !operatorStack.peek().equals("("))
                calculate();

            // 열린 괄호를 제거
            if (operatorStack.contains("(")) {
                operatorStack.pop();
                txt.setText(txt.getText() + actionCommand);
            }
        } else {    // 숫자 버튼이 클릭되면
            // 연산 이후 바로 숫자가 입력되었을 때 계산기 초기화
            if (newCalculator) {
                txt.setText("");
                valueStack.clear();
                operatorStack.clear();
                newCalculator = false;
            }

            // 두자리수 이상의 숫자를 처리하기. 숫자 버튼 클릭 이전에 숫자였다면
            if (!valueStack.isEmpty() && Character.isDigit(txt.getText().charAt(txt.getText().length() - 1))) {
                String lastValue = valueStack.pop();
                valueStack.push(lastValue + actionCommand); // 문자열로 더해준 후 스택에 추가한다.
                // 문자열끼리 더하면 문자가 이어짐. "9" + "1" = "91"
            } else {    // 한자리수 숫자라면 그대로 스택에 추가
                valueStack.push(actionCommand);
            }

            txt.setText(txt.getText() + actionCommand); // 화면에 출력
        }
    }

    // 연산자 우선순위를 반환하는 메서드
    private int priority(String operator) {
        return switch (operator) {  // 반환되는 숫자가 클수록 높은 우선순위
            case "x", "/" -> 2; // 곱하기와 나누기는 2 반환
            case "+", "-" -> 1; // 더하기와 빼기는 1 반환
            default -> 0;   // 그 외는 0 반환
        };
    }

    // 계산하는 메서드
    private void calculate() {
        if (valueStack.size() < 2) return;  // 숫자가 부족하면 계산 중단

        // 스택 특성상 선입후출이기 때문에 num2 먼저 값을 받아준다.
        int num2 = Integer.parseInt(valueStack.pop());
        int num1 = Integer.parseInt(valueStack.pop());
        String operator = operatorStack.pop();

        // 각 연산자 별로 연산 수행
        switch (operator) {
            case "+" -> valueStack.push((num1 + num2) + "");
            case "-" -> valueStack.push((num1 - num2) + "");
            case "x" -> valueStack.push((num1 * num2) + "");
            case "/" -> valueStack.push((num1 / num2) + "");
        }
    }


    public static void main(String[] args) {
        new Calculator();
    }
}
