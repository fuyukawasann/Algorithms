package P056_최단경로구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, K;
	static int[] minDist;
	static boolean[] visited;
	static List<Node>[] adjList;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P056_최단경로구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 장점의 개수와 간선의 개수를 저장함
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 시작점 번호를 저장함
		K = Integer.parseInt(br.readLine());
		
		// adjList 생성
		int u, v, w;
		adjList = new ArrayList[V+1];
		minDist = new int[V+1];
		visited = new boolean[V+1];
		
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
			minDist[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			// adjList에 간선의 정보를 저장한다.
			adjList[u].add(new Node(v, w));
		}
		
		// 다익스트라 알고리즘으로 구현
		dijkstra(K);
		

	}
	
	static void dijkstra(int start) {
		StringBuilder sb = new StringBuilder();
		// 1. 우선순위 큐를 선언한다.
		PriorityQueue<QueueNode> pq = new PriorityQueue<>();
		// 2. 시작 노드와 가중치를 설정한다. 그 뒤 우선순위 큐에 넣는다.
		// 누적 가중치는 minDist에 저장한다.
		minDist[start] = 0;
		pq.offer(new QueueNode(start));
		// pq가 다 빌 때까지 실행
		while(!pq.isEmpty()) {
			// 1. pq에서 원소 하나를 빼옴
			QueueNode currentNode = pq.poll();
			// 1-1. 방문체크
			if(visited[currentNode.to]) {
				continue;
			}
			else {
				// 2. 체크인 
				visited[currentNode.to] = true;
				// 3. adjList(갈 곳)을 비교
				for(Node nextNode : adjList[currentNode.to]) {
					// dist 조건 확인
					int nextWeight = currentNode.sumWeight + nextNode.weight;
					if(minDist[nextNode.to] > nextWeight) {
						minDist[nextNode.to] = nextWeight;
					}
					pq.offer(new QueueNode(nextNode.to));
				}
				
			}
			
		}
		
		// 이제 minDist를 출력 1~V까지
		for(int i = 1; i <= V; i++) {
			if(minDist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}
			else {
				sb.append(minDist[i]).append("\n");
			}
		}
		
		// 출력
		System.out.println(sb.toString());
		
	}
	
	static class QueueNode implements Comparable<QueueNode> {
		int to;
		int sumWeight;
		public QueueNode(int to) {
			this.to = to;
			this.sumWeight = minDist[to];
		}
		
		@Override
		public int compareTo(QueueNode o2) {
			// TODO Auto-generated method stub
			return sumWeight - o2.sumWeight;
		}
		
		
	}
	
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

}
