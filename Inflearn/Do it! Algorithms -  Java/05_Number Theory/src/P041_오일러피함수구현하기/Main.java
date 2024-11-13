package P041_오일러피함수구현하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P041_오일러피함수구현하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Long.parseLong(br.readLine());
		
		long result = n;
		
		for(long p = 2; p * p <= n; p++) {
			// p가 소인수인지 확
			if(n % p == 0) {
				// P[i] = P[i] - P[i] / K (오일러 피 함수)
				result = result - result / p;
				while(n % p == 0) {
					n /= p;
				}
			}
		}
		if(n > 1)
			result = result - result / n;
		System.out.println(result);

	}

}
