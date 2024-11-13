package P2606_바이러스;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// 입력설정
		System.setIn(new FileInputStream("src/P2606_바이러스/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N 받기
		N = Integer.parseInt(br.readLine());
		// M 받기
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 배열 받기
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// 방문하기
		System.out.println(bfs(1));
	}
	
	static int bfs(int start) {
		// 큐 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// visit 설정
		visited[start] = true;
		// count
		int count = 0;
		// 큐에 넣는다.
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼낸다
			int now = queue.poll();
			// 2. 목적지인가?
			// 3. 순회한다.
			for(int next : adjList[now]) {
				// 4. 갈 수 있는가?
				if(!visited[next]) {
					// 5. 체크인
					visited[next] = true;
					count++;
					// 6. 큐에 넣는다.
					queue.offer(next);
				}
				
			}
			
		}
		
		// 큐 반환
		return count;
	}

}
