package P026_DFS와BFS프로그램;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, V;
	
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P026_DFS와BFS프로그램/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M, V
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// visited와 adjList 설정
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 연결 정보 입력
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		// DFS로 결과출력
		DFS(V);
		System.out.print("\n");
		// BFS로 결과출력
		BFS(V);

	}
	
	static void DFS(int vertex) {
		// 1. 체크인
		visited[vertex] = true;
		// 2. 목적지인가?
		
		System.out.print(vertex);
		System.out.print(" ");
		// 3. 순회
		// 전에 adjList를 정렬
		// 한 번만 하면 정렬이 된 상태로 유지됨
		Collections.sort(adjList[vertex]);
		for(int next : adjList[vertex]) {
			// 4. 갈 수 있는가?
			if(!visited[next]) {
				// 5. 간다
				DFS(next);
			}
		}
//		
//		// 6. 체크아웃
//		visited[vertex] = false;
	}
	
	static void BFS(int start) {
		// visited 초기화
		Arrays.fill(visited, false);
		// 큐 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.offer(start);
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼내온다.
			int now = queue.poll();
			// 출력
			sb.append(now).append(" ");
			// 2. 목적지 인가? -> 없어도 됌
			// 3. 큐의 인접 리스트를 순회
			for(int next : adjList[now]) {
				// 4. 갈 수 있는가?
				if(!visited[next]) {
					// 5. 체크인
					visited[next] = true;
					// 6. 간다
					queue.offer(next);
				}
			}
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}

}
