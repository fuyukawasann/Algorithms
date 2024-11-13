package P4134;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P4134/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        while (T-- > 0) {
            long n = Long.parseLong(br.readLine());
            if (n <= 1) n = 2; // 1보다 작은 값은 2로 시작
            
            while (true) {
                if (isPrime(n)) {
                    sb.append(n).append("\n");
                    break;
                }
                n++;
            }
        }
        
        System.out.print(sb.toString());
    }

    // 소수 판별 함수
    public static boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }

        return true;
    }
}