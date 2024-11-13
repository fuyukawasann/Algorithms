package P17396;

import java.io.*;
import java.util.*;

public class Main {
	
	static final long INF = 3 * 10_000_000_000L;
	static int N, M;
	
	static boolean[] visited;
	static long[] dist;
	static boolean[] cantGo;
	static ArrayList<Edge>[] adjList;
	
	static class Edge implements Comparable<Edge> {
		int to;
		long cost;
		
		public Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P17396/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		dist = new long[N];
		cantGo = new boolean[N];
		adjList = new ArrayList[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(Integer.parseInt(st.nextToken()) == 0) {
				cantGo[i] = false;
			} else {
				cantGo[i] = true;
			}
			
			dist[i] = INF;
			adjList[i] = new ArrayList<>();
		}
		
		// 인접 리스트를 완성한다.
		int from, to, cost;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			if(cantGo[to] && to != N-1) {
				
			} else {
				adjList[from].add(new Edge(to, cost));
			}
			if(cantGo[from] && from != N-1) {
				
			} else {
				adjList[to].add(new Edge(from, cost));;
			}
//			adjList[from].add(new Edge(to, cost));
//			adjList[to].add(new Edge(from, cost));
		}
		
		// 다익스트라로 해결
		dijkstra(0);
		
		// 만약 도착지의 dist가 INF면 -1를 출력
		if(dist[N-1] == INF) System.out.println(-1);
		else System.out.println(dist[N-1]);
	}
	
	static void dijkstra(int start) {
		// 큐를 선언
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		// 첫 번째 요소의 Dist를 0으로 초기화
		dist[start] = 0;
		queue.offer(new Edge(start, 0));
		
		while(!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if(visited[current.to]) continue;
			visited[current.to] = true;
			
			for(Edge next : adjList[current.to]) {			
				if(dist[next.to] > current.cost + next.cost) {
					dist[next.to] = current.cost + next.cost;
					queue.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
		
	}

}
