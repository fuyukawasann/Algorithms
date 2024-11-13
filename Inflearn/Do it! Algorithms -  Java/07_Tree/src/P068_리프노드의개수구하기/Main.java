package P068_리프노드의개수구하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, delete, answer;
	
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P068_리프노드의개수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 리스트 선언
		visited = new boolean[N];
		adjList = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// root 저장할 것
		int root = -1;
		
		// adjList 채우기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			// parent가 -1이면 root로 지정
			if(parent == -1) root = i;
			else {
				// 부모에만 자식을 넣음
				adjList[parent].add(i);
				adjList[i].add(parent);
			}
		}
		
		// 지울 번호를 받고 그에 해당하는 인접 리스트를 clear
		delete = Integer.parseInt(br.readLine());
		
		if(delete == root) System.out.println(0);
		else {
			dfs(root);
			System.out.println(answer);
		}
		
	}
	
	static void dfs(int node) {
		visited[node] = true;
		int cNode = 0;
		for(int i : adjList[node]) {
			if(visited[i] == false && i != delete) {
				cNode++;
				dfs(i);
			}
		}
		if(cNode == 0) {
			answer++;
		}
	}

}
