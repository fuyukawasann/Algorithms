package P19113;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P19113/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			int N = Integer.parseInt(br.readLine());
			
			int[] cost = new int[2 * N];
			boolean[] isOriginal = new boolean[2 * N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 2 * N; i++)
			{
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			// 진행
			for(int i = 0; i < 2 * N; i++)
			{
				if(isOriginal[i]) continue;
				
				// 탐색
				double target = cost[i] * (double)4 / 3;
				
				for(int j = i + 1; j < 2 * N; j++)
				{
					if(isOriginal[j]) continue;
					if(target == cost[j]) {
						isOriginal[j] = true;
						sb.append(cost[i]).append(" ");
						break;
					}
				}
			}
			sb.append("\n");
			
			bw.write(sb.toString());
			bw.flush();
			
			
		}
		
		// 자원반환
		bw.close();
		br.close();

	}

}
