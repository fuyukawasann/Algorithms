package P6497;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	
	static int[] parent;
	static Edge[] edgeList;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
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
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P6497/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			// V와 E를 읽는다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// V와 E가0이면 입력 종료
			if(V == 0 && E == 0) break;
			
			// parent를 만든다.
			parent = new int[V];
			
			// parent를 초기화 한다.
			init();
			
			// 간선의 정보를 만든다.
			edgeList = new Edge[E];
			int from, to, cost;
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, cost);
			}
			
			// 크루스칼 알고리즘을 응용하여 아낄 수 있는 최대 액수를 구한다.
			StringBuilder sb = new StringBuilder();
			sb.append(Kruskal()).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int Kruskal() {
		int maxCost = 0;
		
		// 정렬
		Arrays.sort(edgeList);
		
		// 간선의 수만큼 실행
		for(int e = 0; e < E; e++) {
			// 만약 이미 부모가 같다면
			if(find(edgeList[e].from) == find(edgeList[e].to)) {
				// maxCost에 값을 반영하고 continue
				maxCost += edgeList[e].cost;
			}
			
			// 아니라면 union
			union(edgeList[e].from, edgeList[e].to);
			
		}
		
		// 최대 비용을 구했으면 비용 반환
		return maxCost;
		
	}
	
	static void init() {
		for(int i = 0; i < V; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if(pA != pB) parent[pB] = pA;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
