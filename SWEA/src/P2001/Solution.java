package P2001;

import java.io.*;
import java.util.*;

class Solution {
	
	static int[][] map;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P2001/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			// N과 M을 받는다.
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// N * N짜리 지도를 만든다.
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 가능한 끝점에서 합산을 구한다.
			int max = 0;
			for(int i = M - 1; i < N; i++) {
				for(int j = M - 1; j < N; j++) {
					max = Math.max(max, findFlies(i, j));
				}
			}
			
			// 최댓값을 입력한다.
			sb.append(max).append("\n");
			
			// 최댓값을 출력한다.
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static int findFlies(int row, int col) {
		// 입력 받은 지점에서 M 만큼을 더한다.
		int result = 0;
		
		for(int i = row - M + 1; i <= row; i++) {
			for(int j = col - M + 1; j <= col; j++) {
				result += map[i][j];
			}
		}
		
		return result;
	}

}
