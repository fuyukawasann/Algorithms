package P067_트리의부모찾기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] parent; // bfs로 탐색할 것!
	static boolean[] visited;
	
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P067_트리의부모찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N
		N = Integer.parseInt(br.readLine());
		
		// Declare the Array
		parent = new int[N + 1];
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		
		// Initialize adjList
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// Save adjList
		for(int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// Find parent using BFS
		bfs(1);
		
		// parent를 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static void bfs(int root) {
		// Define Queue
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// put into root at queue
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			// 큐에서 꺼내오기
			int now = queue.poll();
			// visited 체크하기
			visited[now] = true;
			
			// 인접리스트 순회
			for(int next : adjList[now]) {
				// 방문 안했을 때만
				if(!visited[next]) {
					// 부모설정
					parent[next] = now;
					queue.offer(next);
				}
			}
		}
		
	}

}
