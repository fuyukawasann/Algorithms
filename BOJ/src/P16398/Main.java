package P16398;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static int[] parent;
	static Edge[] edgeList;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long cost;
		
		public Edge(int from, int to, long cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// V를 읽어온다.
		V = Integer.parseInt(br.readLine());
		
		// parent를 생성하고 초기화한다.
		parent = new int[V];
		init();

		// 간선의 정보를 저장한다.
		edgeList = new Edge[V * (V - 1) / 2];
		int idx = 0;
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				long cost = Long.parseLong(st.nextToken());
				if (j > i) { // i == j 일 때는 건너뛰고, 중복 간선도 건너뜀
					edgeList[idx++] = new Edge(i, j, cost);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Kruskal()).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static long Kruskal() {
		long mstCost = 0;
		int edgeCount = 0;
		
		// 정렬
		Arrays.sort(edgeList);
		
		for (int e = 0; e < edgeList.length; e++) {
			if (find(edgeList[e].from) != find(edgeList[e].to)) {
				union(edgeList[e].from, edgeList[e].to);
				mstCost += edgeList[e].cost;
				edgeCount++;
				
				if (edgeCount == V - 1) {
					break;
				}
			}
		}
		
		return mstCost;
	}
	
	static void init() {
		for (int i = 0; i < V; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if (pA != pB) {
			parent[pB] = pA;
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) {
			return a;
		} else {
			return parent[a] = find(parent[a]);
		}
	}
}