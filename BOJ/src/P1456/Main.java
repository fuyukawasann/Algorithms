package P1456;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P1456/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int max = (int) Math.sqrt(B) + 1;
        boolean[] isPrime = new boolean[max + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // 에라토스테네스의 체로 소수를 구함
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        long count = 0;

        // 소수의 거듭제곱으로 거의 소수를 구함
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                long num = i;
                while (true) {
                    num *= i;
                    if (num > B) break;
                    if (num >= A) count++;
                }
            }
        }

        System.out.println(count);
    }
}