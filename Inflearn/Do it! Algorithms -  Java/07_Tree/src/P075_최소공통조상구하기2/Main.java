package P075_최소공통조상구하기2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final int LOG = 17;

	static int N, M;

	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static int[] depth;
	static int[][] parent;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P075_최소공통조상구하기2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N을 받고 visited, depth, parent 생성
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		depth = new int[N+1];
		parent = new int[LOG+1][N+1];
		
		// 입력을 받고 List를 생성한다.
		adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		int n1, n2;
		for(int n = 0; n < N-1;n++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			
			// 간선 정보를 저장 - 무향성이기 때문에...
			adjList[n1].add(n2);
			adjList[n2].add(n1);
		}
		
		// 1. 부모를 먼저 계산
		bfs(1);
		
		// 2. 조상을 먼저 계산
		findAncestors();
		
		// 3. LCA를 찾는다.
		M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int a, b;
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		// 4. 출력
		System.out.println(sb.toString());
	}
	
	static void bfs(int root) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// 1번 노드가 루트 노드임
		queue.add(root);
		depth[root] = 0;
		visited[root] = true;
		
		while(!queue.isEmpty()) {
			int currentNode = queue.poll();
			
			for(int nextNode : adjList[currentNode]) {
				if(!visited[nextNode]) {
					visited[nextNode] = true;
					
					parent[0][nextNode] = currentNode;
					depth[nextNode] = depth[currentNode] + 1;
					queue.add(nextNode);
				}
			}
		}
	}
	
	static void findAncestors() {
		for(int k = 1; k <= LOG; k++) {
			for(int v = 1; v <= N; v++) {
				parent[k][v] = parent[k-1][parent[k-1][v]];
			}
		}
	}
	
	static int lca(int a, int b) {
		// 0. b의 depth가 항상 크도록 조정한다. -> 코드 간결화, b만 올리기 위해서
		if(depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 1. depth를 일단 맞춰본다.
		for(int k = LOG; k >= 0; k--) {
			if(depth[b] - depth[a] >= (1<<k)) {
				b = parent[k][b];
			}
		}
		
		// 2. 1에서 depth를 맞추었는데 a와 b가 같다면 lca 찾음
		if(a == b) return a;
		
		// 3. 같이 올리면서 처음으로 조상 같지 않은 지점까지 이동
		for(int k = LOG; k >= 0; k--) {
			// 만약 a와 b의 조상이 같지 않으면, a와 b를 2^k만큼 상승함
			if(parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		// 4. 3이 LCA 바로 밑 지점까지 한 것이므로 a, b의 부모를 LCA로 설정한다.
		
		return parent[0][a];
	}

}
