package P1979;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1979/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N과 K를 받는다.
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
			
			// 빈칸을 받는다.
			int[][] blank = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					blank[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1일 때만 탐색을 한다.
			int result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 0일 때는 건너뛴다.
					if(blank[i][j] == 0) continue;
					
					// 1일 때는 탐색한다.
					// 1. 오른쪽 탐색
					// 1) 만약 왼쪽으로 갈 수 있다면 이 지점은 단어 시작점이 아님
					// 왼쪽이 맵 안일 때
					boolean rightValid = true;
					if(0 <= j - 1 && j - 1 < N) {
						// 1이면 유효하지 않음
						if(blank[i][j - 1] == 1) rightValid = false;
					}
					// 2) 아니면 오른쪽으로 1이 몇 개 있는지 탐색
					int count = 1;
					if(rightValid) {
						// 오른쪽 맵 끝까지 가거나 0이 나올 때까지 간다.
						int x = j + 1;
						while(x < N) {
							// 0인지 확인하고 0이면 break
							if(blank[i][x] == 0) break;
							// 1이면 count를 올리고 x 업데이트
							count++;
							x++;
						}
					}
					// count가 k와 같다면 result를 올림
					if(count == K) result++;
					// 2. 아래쪽 탐색
					// 1) 만약 위쪽으로 갈 수 있따면 이 지점은 단어 시작점이 아님
					boolean underValid = true;
					// 위쪽이 맵 안일 때
					if(0 <= i - 1 && i - 1 < N) {
						// 1이면 유효하지 않음
						if(blank[i - 1][j] == 1) underValid = false;
					}
					// 2) 아니면 아래족으로 1이 몇 개 있는지 탐색
					count = 1;
					if(underValid) {
						// 아래쪽으로 끝까지 가거나 0이 나올 때까지 간다.
						int y = i + 1;
						while(y < N) {
							// 0인지 확인하고 0이면 break
							if(blank[y][j] == 0) break;
							// 1이면 count를 올리고 y 업데이트
							count++;
							y++;
						}
					}
					// count가 K와 같다면 result를 올림
					if(count == K) result++;
				}
			}
			
			// 정답 입력
			sb.append(result).append("\n");
			
			// 정답 출력
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
