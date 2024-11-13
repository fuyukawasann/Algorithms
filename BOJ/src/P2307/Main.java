package P2307;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 5000 * 10_000 + 1;
	static int N, M;
	
	static boolean[] visited;
	static boolean[][] cantVisit;
	static ArrayList<Edge>[] adjList;
	static ArrayList<Integer>[] visit;
	static boolean isInitial;
	
	static class Edge implements Comparable<Edge>{
		int to, cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P2307/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		cantVisit = new boolean[N+1][N+1];
		visit = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			visit[i] = new ArrayList<>();
		}
		
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Edge(to, cost));
			adjList[to].add(new Edge(from, cost));
		}
		
		// dijkstra
		isInitial = true;
		int min_cost = dijkstra(1);
		
		// 반복문 돌면서 체크
		isInitial = false;
		boolean isMIDTerminate = false;
		int max_cost = 0;
		for(int i = 1; i < visit[N].size(); i++) {
			// canVisit
			int before = visit[N].get(i - 1);
			int now = visit[N].get(i);
			cantVisit[before][now] = true;
			cantVisit[now][before] = true;
			// 다익스트라 실행
			max_cost = Math.max(max_cost, dijkstra(1));
			// 만약 최댓값이 INF면 중단
			if(max_cost == INF) {
				isMIDTerminate = true;
				break;
			}
			// canVisit 다시 false로 바꿈
			cantVisit[before][now] = false;
			cantVisit[now][before] = false;
			
		}
		
		if(isMIDTerminate) System.out.println(-1);
		else System.out.println(max_cost - min_cost);

	}
	
	static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dist[start] = 0;
		// 만약 초기 다익스트라면 visit에 넣기
		if(isInitial) visit[start].add(start);
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if(visited[current.to]) continue;
			visited[current.to] = true;
			
			for(Edge next : adjList[current.to]) {
				// 만약 cantVisit이 true면 continue;; 해당 간선으로는 갈 수 없음을 의미
				if(cantVisit[current.to][next.to]) continue;
				
				if(dist[next.to] > current.cost +next.cost) {
					dist[next.to] = current.cost + next.cost;
					// 만약 초기 다익스트라면 current의 visit를 복제 후 나를 추가
					if(isInitial) {
						visit[next.to] = (ArrayList<Integer>) visit[current.to].clone();
						visit[next.to].add(next.to);
					}
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
		
		// dist[N]을 반환
		return dist[N];
	}

}
