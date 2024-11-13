package P063_케빈베이컨의6단계법칙;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P063_케빈베이컨의6단계법칙/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 초기화
		dist = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		// 배열 관계를 받는다.
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			dist[A][B] = 1;
			dist[B][A] = 1;
		}
		
		// 플로이드-워셜
		floydWarshall();
		
		// 인접 리스트를 탐색하면서 최솟값을 찾음
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int min = INF;
		for(int i = 1; i <= N; i++) {
			int kevin = 0;
			boolean hasMaxVal = false;
			for(int j = 1; j <= N; j++) {
				// i == j면 건너뛴다.
				if(i == j) continue;
				// 지금 dist가 INF면 케빈을 INF로 설정한다.
				if(dist[i][j] == INF) {
					kevin = INF;
					hasMaxVal = true;
				}
				// 아니면
				else {
					if(!hasMaxVal) {
						kevin += dist[i][j];
					}
				}
			}
			
			// 지금 min과 kevin이 같다. -> pq에 추가
			if(min == kevin) pq.offer(i);
			// min보다 kevin이 작다 -> pq 비우고 나를 추가
			else if(min > kevin) {
				pq.clear();
				pq.offer(i);
				min = kevin;
			}
		}
		
		// pq가 안 비어 있을 때만 답을 출력
		if(!pq.isEmpty()) System.out.println(pq.poll());
		else System.out.println(0);
		

	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					// 만약 둘 중 하나라도 INF면 계산 안함
					if(dist[i][k] == INF || dist[k][j] == INF) continue;
					
					// 아니면 최솟값으로 업데이트
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}

}
