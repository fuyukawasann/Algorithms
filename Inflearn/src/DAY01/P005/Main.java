package DAY01.P005;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY01/P005/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// N, M, R을 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		// 시간표를 저장(0: st 1: et 2:eff)
		int[][] timeTable = new int[M][3];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 3; i++) {
				timeTable[m][i] = Integer.parseInt(st.nextToken());
			}
		}
		// 시간표를 종료 시간 기준으로 정렬
		Arrays.sort(timeTable, (o1, o2) -> (o1[1] - o2[1]));
		
		int[] dp = new int[M];
		for(int i = 0; i < M; i++)
		{
			dp[i] = timeTable[i][2];
		}
		
		int maxEffi = 0;
		
		for(int i = 1; i < M; i++)
		{
			for(int j = i - 1; j >= 0; j--)
			{
				// timeTable[j][1] + R <= timeTable[i][0] 일 때
				if(timeTable[j][1] + R <= timeTable[i][0])
				{
					dp[i] = Math.max(dp[j] + timeTable[i][2], dp[i]);
					
				}
			}
			maxEffi = Math.max(maxEffi, dp[i]);
		}
		
		System.out.println(maxEffi);

	}

}
