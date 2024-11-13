package P2667_단지번호붙이기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	// 조이콘 - 상, 하, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P2667_단지번호붙이기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// map을 받는다.
		for(int i = 0; i < N; i++) {
			int idx = 0;
			for(char c : br.readLine().toCharArray()) {
				if(c == '0') map[i][idx++] = 0;
				else map[i][idx++] = -1;
			}
		}
		
		// map 탐색
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int island = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				
				visited[i][j] = true;
				// map상에서 -1이면 bfs
				// 그 외면 continue
				if(map[i][j] != -1) continue;
				else {
					int count = bfs(i, j, island++);
					// count를 큐에 넣음
					queue.offer(count);
				}
			}
		}
		
		// 정답을 출력
		StringBuilder sb = new StringBuilder();
		sb.append(--island).append("\n");
		while(!queue.isEmpty()) {
			sb.append(queue.poll()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static int bfs(int r, int c, int noIsland) {
		// 큐를 선언
		ArrayDeque<Coordinate> q = new ArrayDeque<>();
		// 시작점 방문 표기
		visited[r][c] = true;
		map[r][c] = noIsland;
		int answer = 1;
		// 큐에 추가
		q.offer(new Coordinate(r, c));
		
		while(!q.isEmpty()) {
			// 1. 큐에서 꺼낸다
			Coordinate now = q.poll();
			// 2. 목적지인가?
			// 3. 순회한다
			for(int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				// 4. 갈 수 있는가? - 지도 안에 있는가? 1인가?
				if(0 <= nr && nr < N && 0 <= nc && nc < N) {
					if(map[nr][nc] != 0 && !visited[nr][nc]) {
						// 5. 체크인
						visited[nr][nc] = true;
						map[nr][nc] = noIsland;
						answer++;
						// 6. 간다
						q.offer(new Coordinate(nr, nc));
					}
				}
				
			}
			
		}
		
		// 정답을 반환
		return answer;
	}
	
	static class Coordinate {
		int r, c;
		
		public Coordinate(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
