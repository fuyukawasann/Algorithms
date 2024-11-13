package P092_빌딩순서구하기;

import java.io.*;

public class Main {
    static final int MOD = 1000000007;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("src/P092_빌딩순서구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int l = Integer.parseInt(input[1]);
        int r = Integer.parseInt(input[2]);

        dp = new long[n + 1][l + 1][r + 1];

        dp[1][1][1] = 1;  // 한 빌딩이 있을 때, 양쪽에서 한 빌딩이 보일 수 있는 경우의 수는 1

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= l; j++) {
                for (int k = 1; k <= r; k++) {
                    dp[i][j][k] = (dp[i-1][j-1][k] + dp[i-1][j][k-1] + (i-2) * dp[i-1][j][k]) % MOD;
                }
            }
        }

        System.out.println(dp[n][l][r]);
    }
}