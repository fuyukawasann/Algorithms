package P15480;

import java.io.*;
import java.util.*;

public class Main {

	static final int LOG = 17;
	
	static int N, M;
	
	// 배열
	static int[] depth;
	static boolean[] visited;
	static int[][] parent;
	static ArrayList<Integer>[] adjList; // 얘는 계속 사용할 예정
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 라이브러리 불러오기
		System.setIn(new FileInputStream("src/P15480/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//간선의 정보를 받는다.
		N = Integer.parseInt(br.readLine());
		
		// 배열 선언
		depth = new int[N+1];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		parent = new int[LOG+1][N+1];
		
		// 인접 리스트를 만든다.
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		// M을 받는다.
		M = Integer.parseInt(br.readLine());
		
		// 부모 찾기 - bfs - 모든 지점에 대한 트리를 일단 만든다
		bfs(1);
		// 조상찾기
		findAncestor();
		
		// 정답 입력을 위한 stringbuilder 선언
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= M; i++) {
	        st = new StringTokenizer(br.readLine());
	        int r = Integer.parseInt(st.nextToken());
	        int u = Integer.parseInt(st.nextToken());
	        int v = Integer.parseInt(st.nextToken());

	        // LCA를 구한다.
	        int lca_uv = lca(u, v);
	        int lca_ru = lca(r, u);
	        int lca_rv = lca(r, v);
	        
	        // 세 개의 LCA 중 가장 깊은 것을 찾는다.
	        int answer = lca_uv;
	        if(depth[answer] < depth[lca_ru]) {
	            answer = lca_ru;
	        }
	        if(depth[answer] < depth[lca_rv]) {
	            answer = lca_rv;
	        }
	        
	        sb.append(answer).append("\n");
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
		// 루트 처리
		depth[root] = 0;
		queue.offer(root);
		visited[root] = true;
		
		// 큐가 빌 때까지 실행
		while(!queue.isEmpty()) {
			// 큐에서 빼옴
			int current = queue.poll();
			
			// 순회
			for(int next : adjList[current]) {
				// 방문 하지 않았을 때 간다
				if(visited[next]) continue;
				else {
					visited[next] = true;
					depth[next] = depth[current] + 1;
					parent[0][next] = current;
					
					// 큐에 넣는다.
					queue.add(next);
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
		// b의 깊이가 더 깊도록 설정
		if(depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// b를 끌어 올림
		for(int k = LOG; k >= 0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) {
				b = parent[k][b];
			}
		}
		
		// a == b면 여기가 lca
		if(a == b) return a;
		
		// 아니면 lca 찾기
		for(int k = LOG; k >= 0; k--) {
			if(parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		// 그 부모가 lca
		return parent[0][a];
	}

}
