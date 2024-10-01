package P2252;

import java.io.*;
import java.util.*;

public class Main {

	static int[] indegree;
	static ArrayList<Integer>[] arrList;
	
	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P2252/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// indegree와 arrList 선언
		indegree = new int[N + 1];
		arrList = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			arrList[i] = new ArrayList<>();
		}
		
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			indegree[B]++;
			arrList[A].add(B);
		}
		
		bw.write(topologicalSort(N));
		bw.flush();
		bw.close();
		br.close();

	}
	
	static String topologicalSort(int num) {
		StringBuilder sb = new StringBuilder();
		
		// 큐를 선언한다.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// indegree가 0인 것들을 담는다.
		for(int i = 1; i <= num; i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		
		while(!queue.isEmpty()) {
			// 1. 큐에서 꺼낸다.
			int now = queue.poll();
			// 2. 스트링 빌더에 넣는다.
			sb.append(now).append(" ");
			// 3. 인접 리스트를 순회한다.
			for(int next : arrList[now]) {
				// 4. 인접 리스트의 indegree를 감소한다.
				indegree[next]--;
				// 5. indegree가 0이면 큐에 넣는다.
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
			
		}
		
		// 정답을 반환한다.
		sb.append("\n");
		return sb.toString();
	}

}
