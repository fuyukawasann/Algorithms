package P5642;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P5642/sample_input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N 처리
			int N = Integer.parseInt(br.readLine());
			
			
			// 값들을 저장한다.
			int[] series = new int[N];
			int[] dp = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				series[i] = Integer.parseInt(st.nextToken());
				dp[i] = series[i];
			}
			
			// dp로 구현한다.
			int max = dp[0];
			for(int i = 1; i < N; i++) {
				dp[i] = Math.max(dp[i], dp[i - 1] + series[i]);
				if(dp[i] > max) max = dp[i]; 
			}
			
			
			// 정답으로 max를 넣는다.
			sb.append(max).append("\n");
			
			// 정답을 출력한다.
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
