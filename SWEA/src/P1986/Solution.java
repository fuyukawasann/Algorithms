package P1986;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/P1986/input.txt"));
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ���� ó��
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// ����
			int N = Integer.parseInt(br.readLine());
			
			int result = 0;
			for(int i = 1; i <= N; i++) {
				// Ȧ���� ���ϰ� ¦���� ��
				if(i % 2 == 1) result += i;
				else result -= i;
			}
			
			// ����� �Է�
			sb.append(result).append("\n");
			
			// ����� ���
			bw.write(sb.toString());
			bw.flush();

		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}

}
