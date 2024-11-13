package P5642;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P5642/sample_input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N ó��
			int N = Integer.parseInt(br.readLine());
			
			
			// ������ �����Ѵ�.
			int[] series = new int[N];
			int[] dp = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				series[i] = Integer.parseInt(st.nextToken());
				dp[i] = series[i];
			}
			
			// dp�� �����Ѵ�.
			int max = dp[0];
			for(int i = 1; i < N; i++) {
				dp[i] = Math.max(dp[i], dp[i - 1] + series[i]);
				if(dp[i] > max) max = dp[i]; 
			}
			
			
			// �������� max�� �ִ´�.
			sb.append(max).append("\n");
			
			// ������ ����Ѵ�.
			bw.write(sb.toString());
			bw.flush();
		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}

}
