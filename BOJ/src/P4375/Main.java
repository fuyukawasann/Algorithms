package P4375;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P4375/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            int num = 1;  // 1로 시작
            int count = 1;  // 자릿수
            
            while (true) {
                if (num % n == 0) {
                    System.out.println(count);
                    break;
                }
                
                // 다음 자리로 넘어갈 때 1을 추가한 새로운 수는 num*10 + 1
                num = (num * 10 + 1) % n;
                count++;
            }
        }
    }
}