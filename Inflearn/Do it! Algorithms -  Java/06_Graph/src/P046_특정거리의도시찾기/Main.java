package P046_특정거리의도시찾기;

import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int N, M, K, X;
	
	static int[] dist;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P046_특정거리의도시찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M, K, X
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		dist = new int[N + 1];
		adjList = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		// adjList 받기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adjList[u].add(v);
		}
		
		// 다익스트라로 수행
		dijkstra(X);
		
		// dist를 순회하면서 K인 것이 있다면 출력
		StringBuilder sb = new StringBuilder();
		boolean isExist = false;
		for(int i = 1; i <= N; i++) {
			if(dist[i] == K) {
				sb.append(i).append("\n");
				isExist = true;
			}
		}
		
		if(!isExist) {
			sb.delete(0, sb.length());
			sb.append(-1).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	static void dijkstra(int start) {
		// 우선순위 큐를 선언
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// dist 초기화
		dist[start] = 0;
		pq.offer(new Node(start, dist[start]));
		
		while(!pq.isEmpty()) {
			// 현재 노드
			Node now = pq.poll();
			
			// 이미 갱신이 되어있다면 안 봄
			if(dist[now.to] < now.cost) continue;
			
			// 아니면 순회
			for(int next : adjList[now.to]) {
				// 갱신 필요하다면 넣는다.
				if(dist[next] > now.cost + 1) {
					dist[next] = now.cost + 1;
					// 갱신했다면 pq에 넣는다.
					pq.offer(new Node(next, dist[next]));
				}
			}
		}
		
	}
	
	static class Node implements Comparable<Node> {
		int to, cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}

}
