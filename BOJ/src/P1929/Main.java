package P1929;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1929/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i = 2; i * i <= N; i++) {
			if(isPrime[i]) {				
				for(int j = i * 2; j <= N; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		for(int i = M; i <= N; i++) {
			if(isPrime[i]) System.out.println(i);
		}
	}

}
