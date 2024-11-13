package P11401;

import java.io.*;

public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P11401/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        long[] factorial = new long[n + 1];
        factorial[0] = 1;

        // 팩토리얼 계산
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i % MOD;
        }

        // nCk = n! * (k! * (n-k)!)^(MOD-2) % MOD
        long numerator = factorial[n];
        long denominator = factorial[k] * factorial[n - k] % MOD;

        // 분모의 역원 계산 (페르마의 소정리)
        long result = numerator * modInverse(denominator, MOD) % MOD;

        System.out.println(result);
    }

    static long modInverse(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    static long pow(long base, int exp, int mod) {
        if (exp == 0) return 1;
        long half = pow(base, exp / 2, mod);
        long halfSquared = half * half % mod;
        if (exp % 2 != 0) {
            return halfSquared * base % mod;
        }
        return halfSquared;
    }
}