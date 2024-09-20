package P1970;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 입력 경로
		System.setIn(new FileInputStream("src/P1970/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		int[] var = new int[] {50_000, 10_000, 5_000, 1_000, 500, 100, 50, 10};
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			
			// count는 돈의 개수를 계산함
			int[] count = new int[8];
			int money = Integer.parseInt(br.readLine());
			
			
			for(int i = 0; i < 8; i++) {
				count[i] = money / var[i];
				money = money - var[i] * count[i];
			}
			
			// 답을 출력
			for(int i = 0; i < 8; i++) {
				sb.append(count[i]).append(" ");
			}
			sb.append("\n");
			
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
