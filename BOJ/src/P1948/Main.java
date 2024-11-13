package P1948;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	
	static ArrayList<Edge>[] adjList;
	
	static long[] time;
	static int[] count;
	static int[] indegree;
	
	static class Edge {
		int to, cost;
		
		public Edge (int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1948/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N과 M을 받는다.
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 배열 선언
		adjList = new ArrayList[N + 1];
		time = new long[N + 1];
		count = new int[N + 1];
		indegree = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 도로를 받는다.
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Edge(to, cost));
			indegree[to]++;
		}
		
		// 시작점, 끝 점을 받는다.
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		topologicalSort(start, end);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(time[end]).append("\n").append(count[end]).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void topologicalSort(int start, int end) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		time[start] = 0;
		count[start] = 0;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			// 순회
			for(Edge next : adjList[current]) {
				// 만약 time[i]와 지금의 값이 같다? count만 추가
				if(time[next.to] == time[current] + next.cost) {
					time[next.to] = time[current] + next.cost;
					count[next.to] += (count[current] + 1);
				}
				// 아니라면 지금보다 시간이 더 클  현재 count + 1로 갱신
				if(time[next.to] < time[current] + next.cost) {
					time[next.to] = time[current] + next.cost;
					count[next.to] = count[current] + 1;
				}
				
				
				// indegree 감소
				indegree[next.to]--;
				
				// 도착 도시면 queue에 넣을 필요 없음 만약 indegree가 0이라면 queue에 추가
				if(indegree[next.to] == 0 && next.to != end) {
					queue.offer(next.to);
				}
				
			}
		}
		
	}

}
