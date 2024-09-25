package P1037;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P1037/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numOfDivisors = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] divisors = new int[numOfDivisors];
		
		for(int i = 0; i < numOfDivisors; i++) {
			divisors[i] = Integer.parseInt(st.nextToken());
		}
		
		// 약수 중 min, max 찾기
		Arrays.sort(divisors);
		
		int minDivisor = divisors[0];
		int maxDivisor = divisors[numOfDivisors - 1];

		
		// 최소 최대 곱이 N
		int result = minDivisor * maxDivisor;
		System.out.println(result);

	}

}
