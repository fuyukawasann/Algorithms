package P004_구간합구하기2;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P004_구간합구하기2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 배열을 선언
		long[][] dp = new long[N + 1][N + 1];
		
		for(int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y = 1; y <= N; y++) {
				// 만약 x가 1이라면 누적합으로 계산한다.
				if(x == 1) {
					dp[x][y] = dp[x][y - 1] + Integer.parseInt(st.nextToken());
				}
				// 그 외라면 위, 아래, 대각을 합하여 계산
				else {
					dp[x][y] = dp[x][y - 1] + dp[x - 1][y] - dp[x - 1][y - 1] + Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 쿼리를 실행
		for(int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 구간합을 계산한다.
			System.out.println(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]);
		}

	}

}
