package P1647;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;
	static Edge[] edgeList;
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		
		public Edge (int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P1647/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// parent를 만들고 List를 선언
		parent = new int[N+1];
		edgeList = new Edge[M];
		
		
		// parent를 init
		init();
		
		// 간선의 정보를 저장
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(a, b, c);
		}
		
		// 크루스갈 실행
		sb.append(Kruskal()).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int Kruskal() {
		int mstCost = 0;
		int edgeCount = 0;
		int maxCost = 0;
		
		Arrays.sort(edgeList);
		
		// M개의 간선동안
		for(int m = 0; m < M; m++) {
			// 현재의 간선을 빼옴
			Edge current = edgeList[m];
			
			// 만약 부모가 이미 같다면 패스
			if(find(current.from) == find(current.to)) {
				continue;
			}
			// 그렇지 않다면 먼저 union
			union(current.from, current.to);
			// 비용합산
			mstCost += current.cost;
			// 간선 계산
			edgeCount++;
			// 이제까지의 간선 중 비용이 높은 것을 업데이트
			maxCost = Math.max(maxCost, current.cost);
			
			// 만약 간선의 개수가 N-1이라면 코스트 반환
			if(edgeCount == N-1) {
				return mstCost - maxCost;
			}
		}
		// 그렇지 않다면 -1 반환
		return -1;
	}
	
	static void init() {
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parent[pB] = pA;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		else return parent[a] = find(parent[a]);
	}

}
