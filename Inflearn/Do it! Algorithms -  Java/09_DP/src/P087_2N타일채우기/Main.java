package P087_2N타일채우기;

import java.io.*;
import java.util.*;

public class Main {
	
	static long mod = 10007;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P087_2N타일채우기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long DP[] = new long[1001];
		DP[1] = 1;
		DP[2] = 2;
		
		for(int i = 3; i <= N; i++) {
			DP[i] = (DP[i - 1] + DP[i - 2]) % mod;
		}
		
		System.out.println(DP[N]);

	}

}
