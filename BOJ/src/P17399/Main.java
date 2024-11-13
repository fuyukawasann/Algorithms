package P17399;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int LOG = 17;
	static int N;
	
	static int[] depth;
	static int[][] parent;
	static boolean[] visited;
	
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P17399/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		// Defiine Array
		depth = new int[N + 1];
		parent = new int[LOG+1][N+1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 인접 리스트 채우기
		for(int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// 부모 설정
		bfs(1);
		
		// 조상 설정
		findAncestor();
		
		// 쿼리 실행
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int q = 1; q <= Q; q++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 쿼리 실행
			// 만약 세 수가 모두 동일하다 -> 본인이 외심
			if(a == b && b == c) {
				sb.append(a).append("\n");
				continue;
			}
			// 그렇지는 않지만, 세 수의 lca로부터의 거리가 모두 동일하다 -> lca가 외심
			int lcas = lca(lca(a, b), c);
			int len_lca_a = depth[a] - depth[lcas];
			int len_lca_b = depth[b] - depth[lcas];
			int len_lca_c = depth[c] - depth[lcas];
			
			if(len_lca_a == len_lca_b && len_lca_b == len_lca_c) {
				sb.append(lcas).append("\n");
				continue;
			}
			// 아니다
			// 다른 세 점들의 중점 중 하나가 외심
			
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void bfs(int root) {
		// 큐 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		depth[root] = 0;
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			
			visited[current] = true;
			
			for(Integer next : adjList[current]) {
				if(!visited[next]) {
					visited[next] = true;
					depth[next] = depth[current] + 1;
					parent[0][next] = current;
					
					queue.offer(next);
				}
			}
		}
		
	}
	
	static void findAncestor() {
		for(int k = 1; k <= LOG; k++) {
			for(int v = 1; v <= N; v++) {
				parent[k][v] = parent[k-1][parent[k-1][v]];
			}
		}
	}
	
	static int lca(int a, int b) {
		if(depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for(int k = LOG; k >= 0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) {
				b = parent[k][b];
			}
		}
		
		if(a == b) return a;
		
		for(int k = LOG; k >= 0; k--) {
			if(parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		return parent[0][a];
	}
}
