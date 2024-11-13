package P10159;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 2000+1;
	static int N, M;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P10159/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dist = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int heavy = Integer.parseInt(st.nextToken());
			int light = Integer.parseInt(st.nextToken());
			dist[light][heavy] = 1;
		}
		
		floydWarshall();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= N; i++) {
			int count = 0;
			for(int j = 1; j <= N; j++) {
				if(dist[i][j] == INF && dist[j][i] == INF) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dist[i][k] == INF || dist[k][j] == INF) continue;
					
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					
				}
			}
		}
	}

}
