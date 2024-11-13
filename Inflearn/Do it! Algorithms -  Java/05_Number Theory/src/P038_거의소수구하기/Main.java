package P038_거의소수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P038_거의소수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int max = 10_000_000;
		
		boolean[] isPrime = new boolean[max + 1];
		
		Arrays.fill(isPrime, true);
		
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i = 2; i <= max; i++) {
			if(isPrime[i]) {
				for(int j = i * 2; j <= max; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		long result = 0;
		for(int i = 2; i < 10_000_001; i++) {
			if (isPrime[i]) {
				long temp = i;
				
				while((double)i <= (double)B/(double)temp) {
					if((double)i >= (double)A/(double)temp) {
						result++;
					}
					temp *= i;
				}
			}
		}
		
		System.out.println(result);

	}

}
