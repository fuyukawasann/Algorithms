package P028_트리의지름구하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int V;
	
	static int[] dist;
	static boolean[] visited;
	static ArrayList<Edge>[] adjList;

	static class Edge {
		int to, cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P028_트리의지름구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// V를 받는다.
		V = Integer.parseInt(br.readLine());
		
		// 배열 초기화
		dist = new int[V + 1];
		visited = new boolean[V + 1];
		adjList = new ArrayList[V + 1];
		
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 총 5번 받는다.
		for(int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while(true) {
				int to = Integer.parseInt(st.nextToken());
				
				if(to == -1) break;
				
				int cost = Integer.parseInt(st.nextToken());
				
				adjList[from].add(new Edge(to, cost));
			}
		}
		
		bfs(1);
		
		// 저 중 최댓값을 출력
		int max = 1;
		for(int i = 1; i <= V; i++) {
			if(dist[max] < dist[i]) {
				max = i;
			}
		}
		dist = new int[V + 1];
		visited = new boolean[V + 1];
		bfs(max);
		Arrays.sort(dist);
		System.out.println(dist[V]);

	}
	
	static void bfs(int start) {
		// 큐 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// 시작점의 dist는 0으로 설정 (이미 되어 있음)
		// visited
		visited[start] = true;
		// 큐에 넣는다.
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼낸다.
			int now = queue.poll();
			// 2. 목적지 인가? - 볼 필요 없음
			// 3. 순회한다.
			for(Edge next : adjList[now]) {
				// 4. 갈 수 있는가?
				if(!visited[next.to]) {
					// 5. 체크인
					visited[next.to] = true;
					if(dist[next.to] < dist[now] + next.cost) {
						dist[next.to] = dist[now] + next.cost;
					}
					// 6. 간다.
					queue.offer(next.to);
				}
			}
		}
		
	}

}
