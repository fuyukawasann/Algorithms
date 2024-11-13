package P048_이분그래프판별하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer>[] adjList;
	static int[] check;
	static boolean[] visited;
	static boolean isEven;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P048_이분그래프판별하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			
			// V, E
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 배열 선언
			adjList = new ArrayList[V + 1];
			visited = new boolean[V + 1];
			check = new int[V + 1];
			isEven = true;
			
			for(int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int Start = Integer.parseInt(st.nextToken());
				int End = Integer.parseInt(st.nextToken());
				
				adjList[Start].add(End);
				adjList[End].add(Start);
			}
			
			for (int i = 1; i <= V; i++) {
				if(isEven) {
					DFS(i);
				} else {
					break;
				}
			}
			
			if(isEven) {
				System.out.println("YES");
			} else System.out.println("NO");
			
		}
	}
	
	static void DFS(int node) {
		visited[node] = true;
		
		for(int i : adjList[node]) {
			if(!visited[i]) {
				check[i] = (check[node] + 1) % 2;
				DFS(i);
			}
			else if (check[node] == check[i]){
				isEven = false;
			}
		}
	}

}
