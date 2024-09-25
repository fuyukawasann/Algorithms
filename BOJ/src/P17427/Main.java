package P17427;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P17427/input.txt"));
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        long sum = 0;
        
        // 각 i에 대해 i의 배수인 수들의 약수 합을 구함
        for (int i = 1; i <= n; i++) {
            sum += (n / i) * i;
        }
        
        System.out.println(sum);
    }
}