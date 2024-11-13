package P091_가장큰정사각형찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P091_가장큰정사각형찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// map 생성
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// dp로 계산
		// dp 생성
		dp = new int[N+1][M+1];
		int max = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i-1][j-1] == '0') continue;
				else {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max * max);
		
	}

}
