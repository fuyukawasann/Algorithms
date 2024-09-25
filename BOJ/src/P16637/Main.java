package P16637;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    static int n;
    static String expression;
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P16637/input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        expression = sc.next();
        
        // 첫 번째 숫자부터 탐색을 시작합니다.
        dfs(0, expression.charAt(0) - '0');
        System.out.println(maxResult);
    }

    // dfs로 모든 가능한 경우의 수를 탐색합니다.
    static void dfs(int index, int currentResult) {
        // 수식의 끝에 도달하면 현재 결과값과 최대값을 비교합니다.
        if (index >= n - 1) {
            maxResult = Math.max(maxResult, currentResult);
            return;
        }

        // 1. 괄호 없이 다음 연산자와 숫자를 처리하는 경우
        char operator = expression.charAt(index + 1);
        int nextNum = expression.charAt(index + 2) - '0';
        int resultWithoutBracket = calculate(currentResult, operator, nextNum);
        dfs(index + 2, resultWithoutBracket);

        // 2. 괄호를 사용하는 경우
        if (index + 4 < n) {
            char nextOperator = expression.charAt(index + 3);
            int nextNextNum = expression.charAt(index + 4) - '0';
            int bracketResult = calculate(nextNum, nextOperator, nextNextNum);
            int resultWithBracket = calculate(currentResult, operator, bracketResult);
            dfs(index + 4, resultWithBracket);
        }
    }

    // 주어진 연산자에 맞게 연산을 수행하는 함수입니다.
    static int calculate(int num1, char operator, int num2) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}