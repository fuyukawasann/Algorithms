package P1984;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/P1984/input.txt"));
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// ���� ó��
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			int[] nums = new int[10];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 10; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// ����
			Arrays.sort(nums);
			
			// ū���� ���� ���� ������ ���� ����
			int sum = 0;
			for(int i = 1; i < 9; i++) {
				sum += nums[i];
			}
			
			// 8�� ������ �ݿø� -> ���� �Է�
			sb.append((int) Math.round((float)sum / 8)).append("\n");
			
			// ���� ���
			bw.write(sb.toString());
			bw.flush();
		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}

}
