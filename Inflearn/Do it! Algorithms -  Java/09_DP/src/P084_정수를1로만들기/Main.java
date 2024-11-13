package P084_정수를1로만들기;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P084_정수를1로만들기/input.txt"));
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] dp = new int[N + 1];
        
        dp[1] = 0; // 1은 연산이 필요 없으므로 0
        
        for (int i = 2; i <= N; i++) {
            // 먼저 1을 빼는 경우
            dp[i] = dp[i - 1] + 1;
            
            // 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            
            // 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        
        System.out.println(dp[N]);
    }
}
