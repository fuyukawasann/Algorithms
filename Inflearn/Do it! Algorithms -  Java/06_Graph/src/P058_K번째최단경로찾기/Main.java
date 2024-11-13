package P058_K번째최단경로찾기;

import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.classfile.Node;

public class Main {
	
	static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P058_K번째최단경로찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N, M, K;
		int[][] W = new int[1001][1001];
		
		// N, M, K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 우선순위 큐 사용
		PriorityQueue<Integer>[] distQueue = new PriorityQueue[N + 1];
		Comparator<Integer> cp = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 < o2 ? 1 : -1;
			}
		};
		for(int i = 0; i < N + 1; i++) {
			distQueue[i] = new PriorityQueue<Integer>(K, cp);
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			W[a][b] = c;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		distQueue[1].offer(0);
		
		while(!pq.isEmpty()) {
			Node u = pq.poll();
			
			for(int adjNode = 1; adjNode <= N; adjNode++) {
				if(W[u.node][adjNode] != 0) {
					if(distQueue[adjNode].size() < K) {
						distQueue[adjNode].offer(u.cost + W[u.node][adjNode]);
						pq.offer(new Node(adjNode, u.cost + W[u.node][adjNode]));
					}
					else if(distQueue[adjNode].peek() > u.cost + W[u.node][adjNode]) {
						distQueue[adjNode].poll();
						distQueue[adjNode].offer(u.cost + W[u.node][adjNode]);
						pq.offer(new Node(adjNode, u.cost + W[u.node][adjNode]));
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if (distQueue[i].size() == K) {
				bw.write(distQueue[i].peek() + "\n");
			} else {
				bw.write(-1 + "\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Node implements Comparable<Node> {
		int node, cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost < o.cost ? 1 : -1;
		}
	}

}
