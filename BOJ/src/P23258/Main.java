package P23258;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;

	static int N, Q;
	
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 라이브러리 불러오기
		System.setIn(new FileInputStream("src/P23258/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, Q 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		dp = new int[N+2][N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int here = Integer.parseInt(st.nextToken());
				for(int c = 1; c <= N+1; c++) {
					dp[c][i][j] = here;
				}
			}
		}
		
	}
	
	static void floydwarshall() {
		for(int c = 1; c <= N+1; c++) {
			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						if(dp[c][i][k] == INF || dp[c][k][j] == INF) continue;
						
						if(dp[c][i][k] > dp[c][i][j] + dp[c][j][k]) {
							dp[c][i][k] = dp[c][i][j] + dp[c][i][k];
						}
					}
				}
			}
		}
		
	}

}
