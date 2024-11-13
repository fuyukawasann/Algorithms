package P1865;

import java.io.*;
import java.util.*;

public class Main {
	
	static final long INF = 12500000000L;
	static int T, N, M, W;
	
	static long[] dist;
	static Edge[] edgeList;
	
	static class Edge {
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1865/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		// TC
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 배열 선언
			edgeList = new Edge[M*2+W];
			
			// 도로를 받는다.
			int from, to, cost;
			for(int i = 0; i < M*2; i+=2) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, cost);
				edgeList[i+1] = new Edge(to, from, cost);
			}
			
			// 웜홀을 받는다.
			for(int i = M*2; i < M*2+W; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, (-1)*cost);
			}
			
			// 벨만 포드를 계산한다.
			StringBuilder sb = new StringBuilder();
			boolean isNegativeCycle = false;
			for(int i = 1; i <= N; i++) {
				
				if(bellmanFord(i)) {;
					isNegativeCycle = true;
					break;
				}
			}
			
			if(isNegativeCycle) sb.append("YES\n");
			else sb.append("NO\n");
			
			// 정답 출력
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		bw.close();
		br.close();
	}
	
	static boolean bellmanFord(int start) {
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		// 시작점 dist는 0으로 초기화
		dist[start] = 0;
		boolean update = false;
		
		for(int i = 1; i <= N - 1; i++) {
			update = false;
			
			for(Edge e : edgeList) {
				// 아직 INF라면 continue
				if(dist[e.from] == INF) continue;
				
				// 아니라면 작을 때만 업데이트
				if(dist[e.to] > dist[e.from] + e.cost) {
					dist[e.to] = dist[e.from] + e.cost;
				}
			}
		}
		
		boolean isNegativeCycle = false;
		for(Edge e : edgeList) {
			// 아직 INF라면 continue
			if(dist[e.from] == INF) continue;
			
			// 아니라면 작을 때만 업데이트
			if(dist[e.to] > dist[e.from] + e.cost) {
				isNegativeCycle = true;
				break;
			}
		}
		
		return isNegativeCycle;
	}

}
