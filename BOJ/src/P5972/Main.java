package P5972;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static final int INF = 5*10_000_000 +1;
	static int N, M;
	
	// 간선의 정보를 저장
	static ArrayList<Edge>[] adjList;
	static int[] dist;
	static boolean[] visited;
	
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P5972/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N과 M을 받는다.
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열을 생성한다.
		dist = new int[N+1];
		adjList = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		// 각 배열을 초기화한다.
		for(int i = 1; i <= N; i++) {
			dist[i] = INF;
			adjList[i] = new ArrayList<>();
		}
		
		// 간선의 정보를 받는다.
		int from, to, cost;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			// 간선의 정보를 인접리스트에 입력한다.
			// 양방향이기 때문에 둘다 넣는다.
			adjList[from].add(new Edge(to, cost));
			adjList[to].add(new Edge(from, cost));
		}
		
		// 다익스트라로 최소 비용(최단거리)를 계산한다.
		dijkstra(1);
		
		// 결과를 출력한다.
		StringBuilder sb = new StringBuilder();
		sb.append(dist[N]).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dijkstra(int start) {
		// 우선순위 큐를 만든다.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 첫 번째 값을 넣는다.
		pq.offer(new Edge(start, 0));
		// 첫 번째 최단거리 갱신
		dist[start] = 0;
		
		// 큐가 빌 때까지 실행
		while(!pq.isEmpty()) {
			// 큐에서 꺼내온다.
			Edge current = pq.poll();
			
			// 이미 비용이 갱신되었으면 건너뛴다.
			if(visited[current.to]) continue;
			
			// 방문을 안했을 때 방문 표기
			visited[current.to] = true;
			
			// 아니면 인접 리스트를 순회한다.
			for(Edge next : adjList[current.to]) {
				// 기존 비용이 클 경우만, 추가하고 pq에 넣는다.
				if(dist[next.to] > dist[current.to] + next.cost) {
					dist[next.to] = dist[current.to] + next.cost;
					
				}
				// pq에 추가
				pq.offer(new Edge(next.to, dist[next.to]));
			}
		}
	}
	
}
