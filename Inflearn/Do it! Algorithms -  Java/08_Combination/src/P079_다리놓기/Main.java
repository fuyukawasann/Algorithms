package P079_다리놓기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static int[][] dp = new int[31][31];
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// T를 받고 테스트 케이스만큼을 계산
		System.setIn(new FileInputStream("src/P079_다리놓기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			// 서쪽의 다리 개수가 더작아 N <= M
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 직접 몇몇 경우를 구한 결과 MCN임.. -> 조합으로 계산한다.
			// 각 경우를 더한 게 정답
			sb.append(combination(M, N));
			sb.append('\n');
		}
		
		// 정답을 출력
		System.out.println(sb.toString());
		
	}
	
	public static int combination(int n, int r)
	{
		if(n == r || r == 0) return 1;
		else if(dp[n][r] != 0) return dp[n][r];
		else {
			return dp[n][r] = combination(n-1,r-1) + combination(n-1,r);
		}
		
	}

}
