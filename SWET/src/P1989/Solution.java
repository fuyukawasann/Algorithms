package P1989;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P1989/input.txt"));
		// 라이브러리 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// 단어를 받는다.
			String word = br.readLine();
			boolean isPalindrome = true;
			// 길이가 짝수일 때
			if(word.length() % 2 == 0) {
				// 길이 절반만큼 검사
				for(int i = 0; i < word.length() / 2; i++) {
					if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
						isPalindrome = false;
						break;
					}
				}
			}
			// 길이가 홀수라면
			else {
				// 딱 절반 검사
				for(int i = 0; i <= word.length() / 2; i++) {
					if(word.charAt(i) != word.charAt(word.length() - 1 - i)) {
						isPalindrome = false;
						break;
					}
				}
			}
			
			// 회문이면 1 아니면 0을 입력
			if(isPalindrome) sb.append(1).append("\n");
			else sb.append(0).append("\n");
			
			// 결과 출력
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
