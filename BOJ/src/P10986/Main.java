package P10986;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, S;
	static long[] nums;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P10986/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// nums 받기
		nums = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken()) + nums[i-1];
		}
		
		int cnt = 0;
		
		for(int i = 0; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if((nums[j] - nums[i]) % M == 0) {
					cnt++;
				}
			}
		}
		
		// 정답을 출력
		System.out.println(cnt);
		
	}

}
