package P1206;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �����
		System.setIn(new FileInputStream("src/P1206/sample_input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// ����
		for(int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N�� �޴´�.
			int N = Integer.parseInt(br.readLine());
			
			// �ǹ� ���̸� �޴´�.
			st = new StringTokenizer(br.readLine());
			
			int[] height = new int[N];
			
			for(int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			// �� �� �� ĭ���� ���� ���� Ȯ��
			long result = 0;
			for(int i = 2; i < N - 2; i++) {
				// �� ��ĭ �� ��ĭ �� ���� ���� �ǹ� ���̿� ���� ������ ���� ��
				int maxL = Math.max(height[i - 1], height[i - 2]);
				int maxR = Math.max(height[i + 1], height[i + 2]);
				int max = Math.max(maxL, maxR);
				
				// ���� ���� - max�� �����ε�
				// ���� ���� �� ������ Ȯ�� ����� ���� ����
				if(height[i] - max > 0) result += (height[i] - max);
			}
			
			// ����� ���
			sb.append(result).append("\n");
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}

}
