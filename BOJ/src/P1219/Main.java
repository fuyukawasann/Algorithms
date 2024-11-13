package P1219;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, start, end;
	
	static Edge[] edgeList;
	static int[] earnMoney;
	static long[] dist;
	
	static class Edge {
		int from, to;
		long cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 라이브러리를 불러온다.
		System.setIn(new FileInputStream("src/P1219/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N, M, start, end
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new long[N]; // 0 ~ N-1 인덱스
		Arrays.fill(dist, Long.MAX_VALUE);
		edgeList = new Edge[M];
		earnMoney = new int[N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, cost);
		}
		
		// 돈 버는 거 추가
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			earnMoney[i] = Integer.parseInt(st.nextToken());
		}
		
		// 벨만-포드로 구현
		StringBuilder sb = new StringBuilder();
		// 음의 사이클이 존재할 
		if(bellmanford()) {
			if(dist[end] == Long.MAX_VALUE) {
				sb.append("gg\n");
			}
			else sb.append("Gee").append("\n");
		}
		else {
			if(dist[end] == Long.MAX_VALUE) {
				sb.append("gg\n");
			}
			else sb.append((-1) * dist[end]).append("\n");
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static boolean bellmanford() {
		
		// dist의 시작점은 언제나 0;;
		dist[start] = (-1) * earnMoney[start];
		
		for(int i = 1; i <= N-1; i++) {
			for(Edge e : edgeList) {
				// 간선을 계속 돌면서 확인
				// i가 1이면 벌 수 있는돈을 cost에 반영
				if(i == 1) {
					e.cost -= (long)earnMoney[e.to]; 
				}
				
				
				// 아직 From이 INF이다 -> 건너뜀
				if(dist[e.from] == Long.MAX_VALUE) continue;
				
				// 아니라면 기존값보다 현재 dist+가중치가 더 작을 때만 반
				if(dist[e.to] > dist[e.from] + e.cost) {
					dist[e.to] = dist[e.from] + e.cost;
				}
			}
		}
		
		// N번째 루프를 확인하고 값이 다르다면 음의 사이클이 있는 거임
		for(Edge e: edgeList) {
			if(dist[e.from] == Long.MAX_VALUE) continue;
			
			if(dist[e.to] > dist[e.from] + e.cost) {
				return true;
			}
		}
		
		return false;
	}

}
