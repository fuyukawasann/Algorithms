package P11736;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P11736/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			int result = 0;
			
			int N = Integer.parseInt(br.readLine());
			int[] number = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++)
			{
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			// ��� ���
			
			for(int i = 2; i <= N - 1; i++)
			{
				boolean isTarget = true;
				
				if(number[i - 1] <= number[i] && number[i] >= number[i + 1]) isTarget = false;
				if(number[i - 1] >= number[i] && number[i] <= number[i + 1]) isTarget = false;
				
				if(isTarget) result++;
			}
			
			// ��� ���
			sb.append(result).append("\n");
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		
		
		// �ڿ���ȯ
		bw.close();
		br.close();

	}

}
