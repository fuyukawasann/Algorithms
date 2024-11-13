package P23632;

import java.io.*;
import java.util.*;

public class Main {

	static int[] indegree;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int N, M, T;
	static int[] count;
	static ArrayList<Integer>[] adjList;
	static boolean[] alreadyBuild;
	static ArrayList<Integer>[] required;
	static boolean[] alreadyCanGetSource;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P23632/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M, T를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		indegree = new int[N+1];
		count = new int[N+1];
		adjList = new ArrayList[N+1];
		alreadyBuild = new boolean[N+1];
		required = new ArrayList[N+1];
		alreadyCanGetSource = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			required[i] = new ArrayList<>();
			count[i] = Integer.MAX_VALUE;
		}
		
		// M개의 이미 지어진 건물을 받는다. -> 이들의 count는 0이 된다.
		st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			int a = Integer.parseInt(st.nextToken());
			count[a] = 0;
			pq.offer(a);
			alreadyBuild[a] = true;
			indegree[a] = -1;
		}
		
		// N개의 건물을 짓는데 어떤 자원이 필요한지 저장
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int iter = Integer.parseInt(st.nextToken());
			for(int j = 1; j <= iter; j++) {
				int source = Integer.parseInt(st.nextToken());
				required[i].add(source);
				if(alreadyBuild[i]) {
					alreadyCanGetSource[source] = true;
				}
			}
		}
		
		
		// 인접 리스트를 생성한다.
		for(int i = 0; i < N - M; i++) {
			st = new StringTokenizer(br.readLine());
			int obj = Integer.parseInt(st.nextToken());
			int many = Integer.parseInt(st.nextToken());
			for(int j = 0; j < many; j++) {
				int b = Integer.parseInt(st.nextToken());
				// 인접 행렬에 넣고 obj의 indegree 상승
				if(!alreadyCanGetSource[b]) {
					adjList[b].add(obj);
					indegree[obj]++;
				}
			}
		}
		
		topologicalSort();
		
		// 자동으로 T보다 작은 것들이 오름차순으로 정렬되어 pq에 들어감
		StringBuilder sb = new StringBuilder();
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void topologicalSort() {
		// 큐를 정의한다.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		// indegree를 순회하며 0인 것들을 넣는다.
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				count[i] = 1;
				if(count[i] <= T) {
					pq.offer(i);
				}
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			// 2번에서 생성하는 자원을 보고 기존에 만들지 않았던 자원이라면
			// 만들 수 있다고 표기하고 해당 자원으로 만들 수 있는 배열들을 넣는다.
			alreadyBuild[current] = true;
			for(Integer source : required[current]) {
				if(!alreadyCanGetSource[source]) {
					// 해당 자원을 만들 수 있다고 표기
					alreadyCanGetSource[source] = true;
					for(Integer next : adjList[source]) {
						// 아직 짓지 않았을 때
						if(!alreadyBuild[next]) {
							indegree[next]--;
							
							// indegree가 0이라면 count를 계산하고 T 이하면 pq에 넣기
							// 그리고 queue에 넣기
							if(indegree[next] == 0) {
								count[next] = count[current] + 1;
								if(count[next] <= T) pq.offer(next);
								queue.offer(next);
							}
						}
					}
				}
			}
		}
	}

}
