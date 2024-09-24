package P1926;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1926/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 읽는다.
		int N = Integer.parseInt(br.readLine());
		
		// 1부터 N을 돌아가며 각 자리에 3, 6, 9가 몇개 있는지 확인한다.
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			boolean is369 = false;
			// 각 자리에 3, 6, 9 검사를 하고 있으면 그 개수 * -를 출력으로 쓰고 나머지는 숫자 출력
			for(char c : String.valueOf(i).toCharArray()) {
				if(c == '3') {
					sb.append("-");
					is369 = true;
				}
				if(c == '6') {
					sb.append("-");
					is369 = true;
				}
				if(c == '9') {
					sb.append("-");
					is369 = true;
				}
			}
			
			// 3, 6, 9가 하나라도 포함 안되었을 때 숫자를 추가
			if(!is369) sb.append(i);
			
			// 각 숫자 처리 후에는 공백을 추가
			sb.append(" ");
		}
		
		// 개행을 추가한 후 출력
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
