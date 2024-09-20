package P1974;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 경로
		System.setIn(new FileInputStream("src/P1974/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// 유효성 검증
			boolean isValid = true;
			
			// 스도쿠 퍼즐을 받음 + 가로 검증
			int[][] map = new int[9][9];
			for(int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				boolean[] hVisited = new boolean[10];
				for(int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 이미 방문되어 있는 거면 유효성 위반
					if(hVisited[map[i][j]]) {
						isValid = false;
					}
					else hVisited[map[i][j]] = true;
				}
			}
			
			// 가로가 유효할 때만 세로 검증을 진행
			if(isValid) {
				// 세로 검증
				for(int i = 0; i < 9; i++) {
					boolean[] vVisited = new boolean[10];
					for(int j = 0; j < 9; j++) {
						// 방문을 이미 했다면 부정 처리
						if(vVisited[map[j][i]]) {
							isValid = false;
							break;
						}
						else vVisited[map[j][i]] = true;
					}
				}
			}
			
			// 세로 검증이 유효할 때만 블록 검정을  진행
			if(isValid) {
				// 시작점이 총 9개
				for(int i = 0; i < 9; i += 3) {
					for(int j = 0; j < 9; j += 3) {
						// 시작점에서 가로 3, 세로 3으로 검정
						boolean[] bVisited = new boolean[10];
						for(int k = i; k < i + 3; k++) {
							for(int l = j; l < j + 3; l++) {
								// 이미 방문한거면 탈락
								if(bVisited[map[k][l]]) {
									isValid = false;
									break;
								}
								else {
									bVisited[map[k][l]] = true;
								}
							}
						}
					}
				}
			}
			
			// 정답 입력
			if(isValid) sb.append(1).append("\n");
			else sb.append(0).append("\n");
			
			// 정답 출력
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
