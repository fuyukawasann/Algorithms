package P9470;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] indegree, maxOrder, count;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P9470/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			// K, M, P
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			// 배열 초기화
			indegree = new int[M + 1];
			maxOrder = new int[M + 1];
			count = new int[M + 1];
			adjList = new ArrayList[M + 1];
			
			for(int m = 1; m <= M; m++) {
				adjList[m] = new ArrayList<>();
			}
			
			// 간선 받기
			for(int p = 0; p < P; p++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
				indegree[to]++;
			}
			
			// 답 저장할 때 K 정답순으로 할
			StringBuilder sb = new StringBuilder();
			sb.append(K).append(" ").append(topologicalSort(M)).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
		
	}
	
	static int topologicalSort(int M) {
		// Queue 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int m = 1; m <= M; m++) {
			if(indegree[m] == 0) {
				// maxOrder를 1, count를 1로 설정
				maxOrder[m] = 1;
				count[m] = 1;
				// 큐에 추가
				queue.offer(m);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			// 순회
			for(int next : adjList[current]) {
				// 만약 maxOrder의 값이 현재와 같다면 next의 count를 증가
				if(maxOrder[next] == maxOrder[current]) {
					count[next]++;
				}
				// maxOrder가 현재의 maxOrder가 클 경우, 현재의 maxOrder로 갱신하고
				// count를 1로 설정
				if(maxOrder[next] < maxOrder[current]) {
					maxOrder[next] = maxOrder[current];
					count[next] = 1;
				}
				
				
				// indegree를 감소
				indegree[next]--;
				
				// 만약 indegree가 0이라면
				if(indegree[next] == 0) {
					// 이 때 카운트가 2이상이면 maxOrder를 1 증가하고
					if(count[next] >= 2) maxOrder[next]++;
					
					// 큐에 추가
					queue.offer(next);
				}
			}
		}
		
		// 결과로 M 노드의 maxOrder를 반환
		return maxOrder[M];
	}

}
