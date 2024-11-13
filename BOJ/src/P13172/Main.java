package P13172;

import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;

    // 모듈러 곱셈 역원을 구하는 함수
    static long modInverse(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    // 모듈러 거듭제곱 계산 함수 (a^b % mod)
    static long pow(long a, long b, int mod) {
        long result = 1;
        a = a % mod;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P13172/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long sum = 0;

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());

            // 모듈러 곱셈 역원을 구해서 곱하기
            long inverseN = modInverse(N, MOD);
            sum = (sum + (S * inverseN) % MOD) % MOD;
        }

        System.out.println(sum);
    }
}