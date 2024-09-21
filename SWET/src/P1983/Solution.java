package P1983;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P1983/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// T.C
		int T = Integer.parseInt(br.readLine());
		String[] s_grade = new String[] {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		
		for(int t = 1; t <= T; t++) {
			// 스트링 빌더
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N, K
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 점수 입력
			ArrayList<Score> score = new ArrayList<>();
			String[] grade = new String[N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// 중간, 기말, 과제
				int mid = Integer.parseInt(st.nextToken());
				int fin = Integer.parseInt(st.nextToken());
				int sub = Integer.parseInt(st.nextToken());
				
				score.add(new Score(i, mid * 0.35 + fin * 0.45 + sub * 0.2));
			}
			
			// 점수를 정렬
			Collections.sort(score);
			
			// 순위 별로 점수를 매긴다.
			int unit = N / 10;
			for(int i = 0; i < score.size(); i++) {
				grade[score.get(i).num] = s_grade[i / unit];
			}
			
			// K번째 수의 등급을 정답으로 입력
			sb.append(grade[K - 1]).append("\n");
			
			// 정답 출력
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 자원 반환
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
