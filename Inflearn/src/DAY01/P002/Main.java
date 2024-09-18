package DAY01.P002;

import java.io.*;
import java.util.*;

public class Main {
	
	static int W, H, T, S;
	
	// 지도
	static int[][] map;
	static boolean[][] visited;
	
	// 조이콘 - 상, 우
	static int[] dx = new int[] {0, 1};
	static int[] dy = new int[] {1, 0};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DAY01/P002/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// W, H, T, S 설정
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		// 배열 초기화
		map = new int[W + 1][H + 1];
		
		
		// 나무 좌표를 받고 지도 초기화
		for(int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1; // 1이 나무가 있는 곳
		}
		
		int max = 0;
		
		for(int x = 0; x <= W - S; x++) {
			for(int y = 0; y <= H - S; y++) {
				max = Math.max(max, bfs(x, y));
			}
		}
		
		System.out.println(max);

	}
	
	static int bfs(int x, int y) {
		// 배열선언
		ArrayDeque<coordinate> queue = new ArrayDeque<>();
		// visited 선언
		visited = new boolean[W + 1][H + 1];
		// 시작점을 큐에 넣는다.
		queue.offer(new coordinate(x, y));
		int count = 0;
		
		// 큐가 빌 때까지 실행
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼내온다.
			coordinate now = queue.poll();
			// 2. 목적지인가?
			// 3. 순회한다.
			for(int i = 0; i < 2; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				// 4. 갈 수 있는가?
				if(x <= nx && nx <= x + S && y <= ny && ny <= y + S) {
					if(!visited[nx][ny]) {
						if(map[nx][ny] == 1) count++;
						// 5. 체크인
						visited[nx][ny] = true;
						// 6. 큐에 넣는다.
						queue.offer(new coordinate(nx, ny));
					}
				}
			}
		}
		
		// 정답 반환
		return count;
	}
	
	static class coordinate {
		int x, y;
		
		public coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
