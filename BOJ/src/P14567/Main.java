package P14567;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] indegree;
	static int[] answer;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P14567/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M 읽기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		indegree = new int[N+1];
		answer = new int[N+1];
		adjList = new ArrayList[N+1];
		
		// adjList 초기화
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// indegree 설정
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			indegree[b]++;
		}
		
		// 위상정렬로 정답 찾기
		topologicalSort(N);
		
		// answer를 돌면서 답을 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static void topologicalSort(int N) {
		// 먼저 큐를 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// in-degree가 0인 것을 찾고 이들의 answer를 1로 설정
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
				answer[i] = 1;
			}
		}
		
		// 큐가 빌 때까지 실행
		while(!queue.isEmpty()) {
			// 큐에서 하나 꺼내옴
			Integer current = queue.poll();
			
			// 인접 리스트를 순회
			for(Integer next : adjList[current]) {
				// indegree를 감소
				indegree[next]--;
				
				// 만약 0이 된다면
				if(indegree[next] == 0) {
					// answer는 현재 과목의 answer + 1
					answer[next] = answer[current] + 1;
					// 큐에 넣음
					queue.offer(next);
				}
			}
		}
	}

}
