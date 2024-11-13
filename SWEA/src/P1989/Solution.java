package P1989;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/P1989/input.txt"));
		// ���̺귯�� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ���� ó��
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// �ܾ �޴´�.
			String word = br.readLine();
			boolean isPalindrome = true;
			// ���̰� ¦���� ��
			if(word.length() % 2 == 0) {
				// ���� ���ݸ�ŭ �˻�
				for(int i = 0; i < word.length() / 2; i++) {
					if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
						isPalindrome = false;
						break;
					}
				}
			}
			// ���̰� Ȧ�����
			else {
				// �� ���� �˻�
				for(int i = 0; i <= word.length() / 2; i++) {
					if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
						isPalindrome = false;
						break;
					}
				}
			}
			
			// ȸ���̸� 1 �ƴϸ� 0�� �Է�
			if(isPalindrome) sb.append(1).append("\n");
			else sb.append(0).append("\n");
			
			// ��� ���
			bw.write(sb.toString());
			bw.flush();
		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}

}
