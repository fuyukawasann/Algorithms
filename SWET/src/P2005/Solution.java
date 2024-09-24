package P2005;

import java.io.*;
import java.util.*;

class Solution {
	
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P2005/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		// dp를 미리 선언한다.
		dp = new int[10][10];
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			
			// N을 받는다.
			int N = Integer.parseInt(br.readLine());
			
			
			// 파스칼의 삼각형을 구한다.
			for(int c = 0; c < N; c++) {
				findPascal(N - 1, c);
			}
			
			// 파스칼 삼각형을 입력한다.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <= i; j++) {
					sb.append(dp[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			// 출력
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static int findPascal(int r, int c) {
		// 만약 c가 0이거나 c가 r과 같다면 1
		if(c == 0 || c == r) return dp[r][c] = 1;
		// 만약 값이 이미 있다면 해당 값을 반환
		else if(dp[r][c] != 0) return dp[r][c];
		// 그렇지 않으면 아래로 탐색
		else return dp[r][c] = findPascal(r - 1, c - 1) + findPascal(r - 1, c);
	}

}
