package P037_소수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P037_소수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[] isPrime = new boolean[1_000_000 + 1];
		
		Arrays.fill(isPrime, true);
		
		// 0과 1은 false다
		isPrime[0] = false;
		isPrime[1] = false;
		
		// 에라토스테네스의 체
		for(int i = 2; i <= 1000000; i++) {
			if(isPrime[i]) {
				for(int j = i * 2; j <= 1000000; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = M; i <= N; i++) {
			if(isPrime[i]) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		

	}

}
