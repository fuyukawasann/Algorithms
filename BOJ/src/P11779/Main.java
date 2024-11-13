package P11779;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<Integer>[] visited;
	static ArrayList<Edge>[] adjList;
	static long[] dist;
	
	static class Edge implements Comparable<Edge>{
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
		System.setIn(new FileInputStream("src/P11779/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 배열 선언
		visited = new ArrayList[N+1];
		adjList = new ArrayList[N+1];
		dist = new long[N+1];
		
		for(int i = 1; i <= N; i++)  {
			visited[i] = new ArrayList<>();
			adjList[i] = new ArrayList<>();
			dist[i] = Long.MAX_VALUE;
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			
			adjList[from].add(new Edge(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).append("\n");
		sb.append(visited[end].size()).append("\n");
		for(int i = 0; i < visited[end].size(); i++) {
			sb.append(visited[end].get(i)).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	
	static void dijkstra(int start) {
		// 우선순위 큐 선언
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 시작 지점의 비용을 0으로 초기화
		dist[start] = 0;
		visited[start].add(start);
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if(dist[current.to] < current.cost) continue;
			
			for(Edge next : adjList[current.to]) {
				if(dist[next.to] > current.cost + next.cost) {
					dist[next.to] = current.cost + next.cost;
					visited[next.to] = (ArrayList<Integer>) visited[current.to].clone();
					visited[next.to].add(next.to);
					
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
		
	}

}
