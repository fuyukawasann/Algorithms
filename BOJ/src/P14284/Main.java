package P14284;

import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 10_000_000 + 1;
	static int N, M;
	
	static int[] dist;
	static boolean[] visited;
	static ArrayList<Edge>[] adjList;
	
	static class Edge implements Comparable<Edge> {
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
		System.setIn(new FileInputStream("src/P14284/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 선언하기
		dist = new int[N + 1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		// 간선 리스트 받기
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Edge(to, cost));
			adjList[to].add(new Edge(from, cost));
		}
		
		// 다익스트라로 최단경로 구하기
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(s, t));
	}
	
	static int dijkstra(int start, int terminate) {
		// 우선순위 큐를 선언
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			// 만약 종료 지점이 최초로 나온다면 -> s와 t가 연결된 거임 종료
			if(current.to == terminate) break;
			
			// 만약 이미 방문한 노드라면 스킵
			if(visited[current.to]) continue;
			
			// 아니라면 방문처리
			visited[current.to] = true;
			
			for(Edge next : adjList[current.to]) {
				if(dist[next.to] > current.cost + next.cost) {
					dist[next.to] = current.cost + next.cost;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}			
		}
		
		// 정답 반환
		// dist가 INF면 -1 아니면 dist 반환
		if(dist[terminate] == INF) return -1;
		else return dist[terminate];
	}

}
