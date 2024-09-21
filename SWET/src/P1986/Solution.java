package P1986;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P1986/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// 연산
			int N = Integer.parseInt(br.readLine());
			
			int result = 0;
			for(int i = 1; i <= N; i++) {
				// 홀수면 더하고 짝수면 뺌
				if(i % 2 == 1) result += i;
				else result -= i;
			}
			
			// 결과를 입력
			sb.append(result).append("\n");
			
			// 결과를 출력
			bw.write(sb.toString());
			bw.flush();

		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
