package P1761;

import java.io.*;
import java.util.*;

public class Main {

	static final int LOG = 17;
	
	static int N, M;
	
	// 배열 선언
	static int[] depth;
	static int[][] parent;
	static int[][] dist;
	
	static boolean[] visited;
	
	static ArrayList<Edge>[] adjList;
	
	static class Edge {
		int to;
		int dist;
		
		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P1761/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		// 배열 선언
		depth = new int[N+1];
		parent = new int[LOG+1][N+1];
		dist = new int[LOG+1][N+1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		
		// 인접 리스트 초기화
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 간선을 입력받는다.
		int from, to, dist;
		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			
			// 간선 정보를 저장한다.
			adjList[from].add(new Edge(to, dist));
			adjList[to].add(new Edge(from, dist));
		}
		
		// 부모를 설정한다. - bfs
		bfs(1);
		// 조상을 설정한다.
		findAncestor();
		// lca를 실행한다.
		// M을 받는다.
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		// 정답을 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs(int start) {
		// 큐를 선언한다.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// 첫 번째 요소의 방문 처리를 한다.
		visited[start] = true;
		// 큐에 첫 번째 원소를 넣는다.
		queue.offer(start);
		// depth를 0으로 설정한다.
		depth[start] = 0;
		
		// 큐가 빌 때까지 실행
		while(!queue.isEmpty()) {
			// 큐에서 꺼내온다.
			int current = queue.poll();
			
			// 큐를 순회한다.
			for(Edge next : adjList[current]) {
				// 방문했는지 확인
				if(visited[next.to]) continue;
				else {
					// 방문 처리
					visited[next.to] = true;
					// 부모 처리
					parent[0][next.to] = current;
					// 깊이 처리
					depth[next.to] = depth[current] + 1;
					// 거리 처리
					dist[0][next.to] = next.dist;
					// 큐에 넣기
					queue.add(next.to);
				}
			}
		}
	}
	
	static void findAncestor() {
		for(int k = 1; k <= LOG; k++) {
			for(int v = 1; v <= N; v++) {
				parent[k][v] = parent[k-1][parent[k-1][v]];
				dist[k][v] = dist[k-1][parent[k-1][v]] + dist[k-1][v];
			}
		}
	}
	
	static int lca(int a, int b) {
		// b의 깊이가 더 깊도록 설정
		if(depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		int distA = 0;
		int distB = 0;
		
		// b의 깊이를 a까지 끌어 올림
		for(int k = LOG; k >= 0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) {
				distB += dist[k][b];
				b = parent[k][b];
			}
		}
		
		// 만약 a == b라면 지금 시점이 lca
		if(a == b) return distA + distB;
		
		// 아니라면 공통조상을 찾을 때까지 실행
		for(int k = LOG; k >= 0; k--) {
			if(parent[k][b] != parent[k][a]) {
				distA += dist[k][a];
				distB += dist[k][b];
				b = parent[k][b];
				a = parent[k][a];
			}
		}
		
		// 그 부모가 lca
		distA += dist[0][a];
		distB += dist[0][b];
		a = parent[0][a];
		b = parent[0][b];
		
		return distA + distB;
	}
}