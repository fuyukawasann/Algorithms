package P074_최소공통조상구하기1;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int LOG = 20;
	static int N, M;
	
	static int[][] parent;
	static boolean[] visited;
	static int[] depth;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P074_최소공통조상구하기1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N 받는다.
		N = Integer.parseInt(br.readLine());
		
		// 배열 선언
		parent = new int[LOG+1][N+1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		depth = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 연결된 곳을 받는다.
		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// 부모를 설정한다.
		bfs(1);
		
		// 조상을 설정한다.
		findAncestor();
		
		// lca 실행
		StringBuilder sb = new StringBuilder();
		// M 받기
		M = Integer.parseInt(br.readLine());
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	static void bfs(int root) {
		// Queue 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// root 처리
		depth[root] = 0;
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			
			// 방문 표시
			visited[current] = true;
			
			// 순회
			for(Integer next : adjList[current]) {
				// 방문하지 않았을 때만 처리
				if(!visited[next]) {
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
		// depth를 b가 더 깊도록 설정
		if(depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// b를 a까지 끌어 올림
		for(int k = LOG; k >= 0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) {
				b = parent[k][b];
			}
		}
		
		// 만약 a와 b가 같다면 그 지점이 lca
		if(a == b) return a;
		
		// 그렇지 않다면 lca 찾기
		for(int k = LOG; k >= 0; k--) {
			if(parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		// 그 부모를 반환
		return parent[0][a];
	}

}
