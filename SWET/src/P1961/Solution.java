package P1961;

import java.io.*;
import java.util.*;

class Solution {
	
	

	public static void main(String[] args) throws Exception {
		// 입력 경로
		System.setIn(new FileInputStream("src/P1961/input.txt"));
		// 라이브러리 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테스트케이스 처리
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 별 실행
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			// 테스트 케이스 출력 처리
			sb.append("#").append(t).append("\n");
			
			// 1. 행렬 처리
			// 1) 줄 수를 받는다.
			int N = Integer.parseInt(br.readLine());
			// 2) 행렬을 받는다.
			int[][] matrix = new int[N][N];
			// 3) 행렬 초기화
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 90도 180도 270도 회전
			// 열단위로 구현
			/*
				741 987 369
				852 654 258
				963 321 147
			 */
			// 마지막행의 각 요소들로 각 열의 시작점이 시작됨
			// 열별 저장
			
			// i가 0일 때
			// 시작점: 마지막행 첫번째
			// 90도 일 때
			for(int i = N - 1; i >= 0; i--) {
				sb.append(matrix[i][0]);
			}
			sb.append(" ");
			
			// 180도 일 때
			for(int i = N - 1; i >= 0; i--) {
				sb.append(matrix[N - 1][i]);
			}
			sb.append(" ");
			
			// 270도 일 때
			for(int i = 0; i < N; i++) {
				sb.append(matrix[i][N - 1]);
			}
			sb.append("\n");
				
			// i가 1 ~ N - 2까
			for(int i = 1; i < N - 1; i++) {
				// 90도 일 때 (위로 읽는다)
				for(int j = N - 1; j >= 0; j--) {
					sb.append(matrix[j][i]);
				}
				sb.append(" ");
				
				
				// 180도 일 때 (왼쪽으로 읽는다)
				for(int j = N - 1; j >= 0; j--) {
					sb.append(matrix[N - 1 - i][j]);
				}
				sb.append(" ");
				
				
				// 270도 일 때 (아래로 읽는다)
				for(int j = 0; j < N; j++) {
					sb.append(matrix[j][N - 1 - i]);
				}
				sb.append("\n");
			}
			
			// i가 N - 1일 때
			// 90도 일 때
			for(int i = N - 1; i >= 0; i--) {
				sb.append(matrix[i][N - 1]);
			}
			sb.append(" ");
			
			// 180도 일 때
			for(int i = N - 1; i >= 0; i--) {
				sb.append(matrix[0][i]);
			}
			sb.append(" ");
			
			// 270도 일 때
			for(int i = 0; i < N; i++) {
				sb.append(matrix[i][0]);
			}
			sb.append("\n");
			
			
			// 출력함
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 사용 종료
		bw.close();
		br.close();

	}

}
