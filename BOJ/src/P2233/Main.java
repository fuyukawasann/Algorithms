package P2233;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int LOG = 11;
	static int N;
	
	static int[] depth;
	static boolean[] visited;
	static int[][] parent;
	static ArrayList<Integer>[] adjList;
	static int[][] binaryCount;
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P2233/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		// 배열 선언
		depth = new int[N+1];
		visited = new boolean[N+1];
		parent = new int[LOG+1][N+1];
		adjList = new ArrayList[N+1];
		binaryCount = new int[N+1][2]; // 0 :0이 들어온 순서, 1 : 1이 들어온 순서
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 이진 수 받기
		char[] binary = br.readLine().toCharArray();
		
		// X Y 먼저 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int X = 0, Y = 0;
				
		int num = 1;
		ArrayList<Integer> stack = new ArrayList<>();
		for(int i = 1; i <= 2*N; i++) {
			if(i == 1) {
				if(x == i) X = num;
				if(y == i) Y = num;
				binaryCount[num][0] = i;
				stack.add(num);
				continue;
			}
			
			// 0이 나오면 -> 스택에 추가, 인접 리스트에 추가, binaryCount 추가
			if(binary[i - 1] == '0') {
				int before = stack.get(stack.size() - 1);
				adjList[num + 1].add(before);
				adjList[before].add(num + 1);
				stack.add(++num);
				binaryCount[num][0] = i;
				if(x == i) X = num;
				if(y == i) Y = num;
			}
			// 1이 나오면 -> 스택에서 제거, binaryCount 추가
			else {
				int now = stack.remove(stack.size() - 1);
				binaryCount[now][1] = i;
				if(x == i) X = now;
				if(y == i) Y = now;
			}
		}
		
		// 부모 찾기
		bfs(1);
		
		// 조상 찾기
		findAncestor();
		
		// lca 수행하기
		int lcas = lca(X, Y);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(binaryCount[lcas][0]).append(" ").append(binaryCount[lcas][1]).append("\n");
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
