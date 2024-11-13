package P025_친구관계파악하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int N, M;
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static boolean arrive;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P025_친구관계파악하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arrive = false;
		
		adjList = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			DFS(i, 1);
			if (arrive) break;
		}
		
		if(arrive) System.out.println("1");
		else System.out.println("0");

	}
	
	public static void DFS(int now, int depth) {
		// 1. 체크인
		visited[now] = true;
		
		// 2. 목적지인가?
		if(depth == 5 || arrive) {
			arrive = true;
			return;
		}
		
		// 3. 순회
		for(int next : adjList[now]) {
			// 4. 갈 수 있는가?
			if(!visited[next]) {
				// 5. 간다
				DFS(next, depth + 1);
			}	
		}
		
		// 6. 체크아웃
		
		visited[now] = false;
		
	}

}
