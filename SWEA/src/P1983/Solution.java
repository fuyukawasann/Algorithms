package P1983;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/P1983/input.txt"));
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		String[] s_grade = new String[] {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		
		for(int t = 1; t <= T; t++) {
			// ��Ʈ�� ����
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N, K
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// ���� �Է�
			ArrayList<Score> score = new ArrayList<>();
			String[] grade = new String[N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// �߰�, �⸻, ����
				int mid = Integer.parseInt(st.nextToken());
				int fin = Integer.parseInt(st.nextToken());
				int sub = Integer.parseInt(st.nextToken());
				
				score.add(new Score(i, mid * 0.35 + fin * 0.45 + sub * 0.2));
			}
			
			// ������ ����
			Collections.sort(score);
			
			// ���� ���� ������ �ű��.
			int unit = N / 10;
			for(int i = 0; i < score.size(); i++) {
				grade[score.get(i).num] = s_grade[i / unit];
			}
			
			// K��° ���� ����� �������� �Է�
			sb.append(grade[K - 1]).append("\n");
			
			// ���� ���
			bw.write(sb.toString());
			bw.flush();
		}
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}
	
	static class Score implements Comparable<Score> {
		int num;
		double score;
		
		public Score(int num, double score) {
			this.num = num;
			this.score = score;
		}

		@Override
		public int compareTo(Score o) {
			// TODO Auto-generated method stub
			return Double.compare(o.score, this.score);
		}
	}

}
