package 기초트레이닝.P005;

import java.io.*;
import java.util.*;


class Main {
	
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P005/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long result = 1;
		
		for(int i = 1; i <= N; i++) {
			result = (result * i) % MOD;
		}
		
		System.out.println(result);
	}
}