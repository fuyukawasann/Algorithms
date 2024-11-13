package P027_미로탐색하기;

import java.io.*;
import java.util.*;


public class Main {

	static int N, M;
	
	static int[][] map;
	static boolean[][] visited;
	static int[][] dist;
	
	// 조이콘 - 상, 하, 좌, 우
	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P027_미로탐색하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		dist = new int[N + 1][M + 1];
		
		// map 세팅
		for(int i = 1; i <= N; i++) {
			int j = 1;
			for(char c : br.readLine().toCharArray()) {
				dist[i][j] = Integer.MAX_VALUE;
				map[i][j++] = c - '0';
				
			}
		}
		
		// bfs로 탐색
		BFS(1, 1);

	}
	
	static void BFS(int r, int c) {
		// 배열 선언
		ArrayDeque<Coordinate> queue = new ArrayDeque<>();
		
		// 방문 설정
		visited[r][c] = true;
		// dist 설정
		dist[r][c] = 1;
		// 큐에 넣기
		queue.offer(new Coordinate(r, c));
		
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼내온다.
			Coordinate now = queue.poll();
			// 2. 목적지인가? - 필요 없음
			// 3. 순회한다
			for(int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				// 4. 방문할 수 있는가? - 지도 안에 있고 1이여야 함
				if(1 <= nr && nr <= N && 1 <= nc && nc <= M) {
					if(map[nr][nc] == 1) {
						// 5. 체크인
						visited[nr][nc] = true;
						// 만약 현재 dist보다 값이 작으면 갱신
						if(dist[nr][nc] > dist[now.r][now.c] + 1) {
							dist[nr][nc] = dist[now.r][now.c] + 1;
							// 6. 방문한다.
							queue.offer(new Coordinate(nr, nc));
						}
					}
				}
				
			}
			
		}
		
		System.out.println(dist[N][M]);
		
	}
	
	static class Coordinate {
		int r, c;
		
		public Coordinate(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
