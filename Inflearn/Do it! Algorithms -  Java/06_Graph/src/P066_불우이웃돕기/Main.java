package P066_불우이웃돕기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int INF;
	static int N;
	static PriorityQueue<Edge> edgeList;
	static int[] parent;
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P066_불우이웃돕기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 배열 선언
		parent = new int[N];
		int totalCount = 0;
		edgeList = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			// parent 선언
			parent[i] = i;
			char[] input = br.readLine().toCharArray();
			
			// 배열 정보 입력
			for(int j = 0; j < N; j++) {
				// c가 0이면 가중치가 0임
				if(input[j] == '0') {
					// edgeList.add(new Edge(i, j, INF));
				}
				// 만약 소문자라면 1 ~ 26
				else if('a' <= input[j] && input[j] <= 'z') {
					edgeList.add(new Edge(i, j, input[j] - 'a' + 1));
					totalCount += (input[j] - 'a' + 1);
				}
				else {
					edgeList.add(new Edge(i, j, input[j] - 'A' + 27));
					totalCount += (input[j] - 'A' + 27);
				}
				
			}
		}
		
		// 크루스칼로 MST 구함
		int result = kruskal();
		
		// 만약 result가 -1이라면 -1 출력
		if(result == -1) System.out.println(-1);
		else {
			// parent를 검사하면서 값이 두 개 이상인지 확인
			HashSet<Integer> hash = new HashSet<>();
			for(int i = 0; i < N; i++) {
				// 해시에 없으면 해시에 추가
				if(!hash.contains(find(i))) {
					hash.add(parent[i]);
				}
			}
			
			// hash 사이즈가 2 이상이면 -1
			if(hash.size() >= 2) System.out.println(-1);
			else System.out.println(totalCount - result);
		}

	}
	
	static int kruskal() {
		// edgeList 정렬되어있는 상태임;;
		int edgeCount = 0;
		int mstCost = 0;
		
		while(!edgeList.isEmpty()) {
			// 만약 count가 N - 1이면 탈출
			if(edgeCount == N - 1) break;
			// 하나 꺼내옴
			Edge now = edgeList.poll();
			// 부모가 같은지 확인 -> 같다면 연결할 필요가 없음..
			if(find(now.from) == find(now.to)) continue;
			// 아니라면 union
			union(now.from, now.to);
			// 값을 더함
			mstCost += now.cost;
			edgeCount++;
		}
		
		// 만약 edgeCount가 N - 1이라면 mstCost를 반환하고 아니라면 -1 반환
		if(edgeCount == N - 1) return mstCost;
		else return -1;
		
		
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parent[pA] = pB;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static class Edge implements Comparable<Edge> {
		int to, from, cost;
		
		public Edge(int from, int to, int cost) {
			this.to = to;
			this.from = from;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}

}
