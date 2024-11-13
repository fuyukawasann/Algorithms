package P064_최소신장트리구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<Edge> edgeList;
	static int[] parent;
	static int N, M;
	
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
		public int compareTo(Edge e) {
			// TODO Auto-generated method stub
			return this.cost - e.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P064_최소신장트리구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 부모 배열을 초기화 한다.
		parent = new int[N+1];
		init();
		
		// 간선 정보를 받는다.
		edgeList = new PriorityQueue<>();
		
		
		
		int a, b, c;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// Edge를 만들고 edgeList에 넣는다.
			edgeList.offer(new Edge(a, b, c));
		}
		
		// 크루스칼 알고리즘으로 최소 신장 트리의 비용을 구한다.
		System.out.println(kruskal());
		
		
	}
	
	static int kruskal() {
		// 비용을 계산할 변수
		int mstCost = 0;
		// 간선의 개수를 셀 변수
		int edgeCount = 0;
		
		while(!edgeList.isEmpty()) {
			// 큐에서 간선을 꺼낸다.
			Edge current = edgeList.poll();
			
			// 이미 같은 집합인지보고 같은 집합이라면 생략
			if(find(current.from) == find(current.to)) continue;
			
			// 그렇지않다면
			// union 후에
			union(current.from, current.to);
			
			// 비용을 최소 신장 트리 비용에 반영한다.
			mstCost += current.cost;
			edgeCount++;
			
			// 만약 간선의 개수가 N-1이면 탈출!
			if(edgeCount == N-1) {
				return mstCost;
			}
		}
		
		// 다 돌았는데도 간선의 개수가 N-1개가 아니면 신장트리 못만듦
		return -1;
	}
	
	// init()
	static void init() {
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		// 만약 부모가 다르다면
		if(parentA != parentB) {
			parent[parentA] = parentB;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		
		else {
			// 단축까지 진
			return parent[a] = find(parent[a]);
		}
	}

}
