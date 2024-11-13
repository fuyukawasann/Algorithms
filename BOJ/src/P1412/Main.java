package P1412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1412/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		adjList = new ArrayList[N];
		
		// map을 받는다.
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			adjList[i] = new ArrayList<>();
		}
		
		// map을 돌면서 확인
		int[] indegree = new int[N];
		
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				if(i == j) continue;
				
				// 양방향 도로일 때 -> 사이클에 영향을 안 줌
				if(map[i][j] == 'Y' && map[j][i] == 'Y') {
					continue;
				}
				else if(map[i][j] == 'Y') {
					indegree[j]++;
					adjList[i].add(j);
				}
				else if(map[j][i] == 'Y') {
					indegree[i]++;
					adjList[j].add(i);
				}
				else continue;
			}
		}
		
		// 위상 정렬 진행
		topologicalSort(N, indegree);
		
		// 정답확인
		boolean checked = false;
		for(int i = 0; i < N; i++) {
			if(indegree[i] != 0) {
				checked = true;
				break;
			}
		}
		
		if(checked) System.out.println("NO");
		else System.out.println("YES");
	}
	
	static void topologicalSort(int N, int[] indegree) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// in-degree 0인 것 넣기
		for(int i = 0; i < N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			
			for(Integer next : adjList[current]) {
				indegree[next]--;
				
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}

}
