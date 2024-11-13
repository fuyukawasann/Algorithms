package P003_구간합구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P003_구간합구하기/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] prefixSum = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		prefixSum[0] = 0;
		
		for(int i = 1; i <= N; i++) {
			prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		// 쿼리 실행
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			System.out.println(prefixSum[j] - prefixSum[i - 1]);
		}

	}

}
