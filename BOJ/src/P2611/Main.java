package P2611;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] indegree;
	static ArrayList<Edge>[] adjList;
	static int[] dp; // 여기에는 최대 비용을 저장함
	static ArrayList<Integer>[] trace; // 여기에는 최대 비용일 때의 경로를 저장함
	
	static class Edge {
		int to, cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 입력 설정
		System.setIn(new FileInputStream("src/P2611/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N, M
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 배열 선언
		indegree = new int[N + 1];
		adjList = new ArrayList[N + 1];
		dp = new int[N + 1];
		trace = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			trace[i] = new ArrayList<>();
		}
		
		// 간선 받기
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 간선 추가
			adjList[from].add(new Edge(to, cost));
			indegree[to]++;
		}
		
		// 위상 정렬 실행
		topologicalSort();
		
		// 정답을 출력
		StringBuilder sb = new StringBuilder();
		
		// 최대가치 출력
		sb.append(dp[1]).append("\n");
		
		// 지나온 경로를 출력
		for(int i : trace[1]) {
			sb.append(i).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void topologicalSort() {
		// 큐를 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// 어차피 1부터 시작할 것임 -> 굳이 indegree 0인 것을 찾아서 넣을 필요 없음
		queue.offer(1);
		// dp[1]을 0으로 초기화
		// 1의 ArrayList에 자신을 추
		dp[1] = 0;
		trace[1].add(1);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			// 연결된 곳을 순회
			for(Edge next : adjList[current]) {
				
				if(dp[next.to] < dp[current] + next.cost) {
					dp[next.to] = Math.max(dp[next.to], dp[current] + next.cost);
					trace[next.to] = (ArrayList<Integer>) trace[current].clone();
					trace[next.to].add(next.to);
				}
				
				
				// 일단 indegree를 깎음
				indegree[next.to]--;
				
				// 만약 indegree가 0이라면
				if(indegree[next.to] == 0 && next.to != 1) {
					queue.offer(next.to);
				}
			}
		}
	}

}
