package P053_줄세우기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Integer>[] adjList;
	static int[] indegree;

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/P053_줄세우기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		for(int n = 1; n <= N; n++) {
			adjList[n] = new ArrayList<>();
		}
		indegree = new int[N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			indegree[to]++;
		}
		
		topologicalSort();
	}
	
	public static void topologicalSort() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		// 차수가 0인 노드 queue에 넣기
		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node + " ");
			
			for (Integer to: adjList[node]) {
				indegree[to]--;
				
				if(indegree[to] == 0) {
					queue.offer(to);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}