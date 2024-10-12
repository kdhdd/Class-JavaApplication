package week5.키패드만들기;

import java.util.Stack;

public class PostfixNotation {
    public static void main(String[] args) {
        String infix = "(10+2)/4";
        String postfix = convPostfix(infix);
        System.out.println("Postfix Notation: " + postfix);
        System.out.println("Calculated Result: " + postfixCalculate(postfix));
    }

    public static double postfixCalculate(String postfix){
        Stack<Double> stack = new Stack<>();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            // 숫자일 경우
            if (Character.isDigit(c)) {
                num.append(c);  // 여러 자리 숫자를 위해 숫자를 StringBuilder에 추가
            }
            else if (c == ' ' && num.length() > 0) {
                // 숫자가 끝난 시점에 스택에 저장
                stack.push(Double.valueOf(num.toString()));
                num.setLength(0);  // StringBuilder 초기화
            }
            // 연산자일 경우
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                double op2 = stack.pop();
                double op1 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(op1 + op2);
                        break;
                    case '-':
                        stack.push(op1 - op2);
                        break;
                    case '*':
                        stack.push(op1 * op2);
                        break;
                    case '/':
                        stack.push(op1 / op2);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static String convPostfix(String infix) {
        Stack<Character> opStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        StringBuilder num = new StringBuilder();  // 다자리 숫자 처리를 위해 사용

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c)) {
                num.append(c);  // 숫자 부분을 따로 저장
            }
            else {
                if (num.length() > 0) {
                    postfix.append(num).append(" ");  // 숫자를 다 모으면 한 번에 출력
                    num.setLength(0);  // 숫자 저장 공간 초기화
                }

                // 연산자 및 괄호 처리
                if (c == '(') {
                    opStack.push(c);
                } else if (c == ')') {
                    while (!opStack.isEmpty() && opStack.peek() != '(') {
                        postfix.append(opStack.pop()).append(" ");
                    }
                    opStack.pop();  // '('를 제거
                } else if (isOperator(c)) {
                    while (!opStack.isEmpty() && compareOp(opStack.peek(), c) >= 0) {
                        postfix.append(opStack.pop()).append(" ");
                    }
                    opStack.push(c);
                }
            }
        }

        if (num.length() > 0) {
            postfix.append(num).append(" ");  // 마지막 숫자 추가
        }

        while (!opStack.isEmpty()) {
            postfix.append(opStack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    // 연산자 확인
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // 연산자 우선순위 반환
    public static int getOpPriority(char c){
        switch (c) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;
            default:
                return -1;
        }
    }

    // 연산자 우선순위 비교
    public static int compareOp(char stackOp, char curOp) {
        return getOpPriority(stackOp) - getOpPriority(curOp);
    }
}
