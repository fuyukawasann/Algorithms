package P1516;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] time, indegree, answer;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1516/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N];
		answer = new int[N];
		indegree = new int[N];
		adjList = new ArrayList[N];
		
		// 인접 리스트 초기화
		for(int i = 0; i < N; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++)
		{
			// 걸리는 시간을 받는다.
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while(true)
			{
				// 만약 지금 받는 값이 -1이면 탈출한다.
				int from = Integer.parseInt(st.nextToken());
				if(from == -1) break;
				
				// 그렇지 않다면 인접 리스트에 추가하고 indegree를 증가한다.
				indegree[i]++;
				adjList[from - 1].add(i);
			}
		}
		
		// 다하면 위상 정렬을 한다.
		topologicalSort();
		
		// answer를 순회하며 정답을 입력한다.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++)
		{
			sb.append(answer[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static void topologicalSort()
	{
		// 처음에 큐를 선언한다.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// indegree를 순회하며 0인 것들을 queue에 넣고 최소 시간을 answer에 넣는다.
		for(int i = 0; i < N; i++)
		{
			if(indegree[i] == 0)
			{
				queue.offer(i);
				answer[i] = time[i];
			}
		}
		
		// 큐가 빌 때까지 실행한다.
		while(!queue.isEmpty())
		{
			// 큐에서 꺼낸다.
			int now = queue.poll();
			// 인접리스트를 순회하며 indegree를 감소한다.
			for(int next : adjList[now])
			{
				indegree[next]--;
				// 현재 시간 + next의 만드는 시간을 합한 것을 answer에 업데이트(Math.max로)
				answer[next] = Math.max(answer[next], answer[now] + time[next]);
				// indegree가 0인 것을 큐에 넣는다.
				if(indegree[next] == 0) queue.offer(next);
			}
			
		}
	}
	
	

}
