package P062_경로찾기;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[][] adjList;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P062_경로찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// 배열 초기화
		adjList = new int[N][N];
		
		// 인접 리스트를 받는다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				// i == j 면 inf를 넣는다.
				int info = Integer.parseInt(st.nextToken());
				if(i == j) adjList[i][j] = INF;
				// 그 외에는
				else {
					// 0이면 INF, 아니면 1
					if(info == 0) {
						adjList[i][j] = INF;
					}
					else {
						adjList[i][j] = 1;
					}
				}
			}
		}
		
		// 플로이드-워셜로 해결
		floydWarshall();
		
		// 정답을 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 만약 INF면 0으로 입력
				if(adjList[i][j] == INF) sb.append(0).append(" ");
				// 그 외는 1로 입력
				else sb.append(1).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void floydWarshall() {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 만약 ik kj 중 하나라도 INF면 연산안함
					if(adjList[i][k] == INF || adjList[k][j] == INF) continue;
					
					// 아니면 최솟값으로 업데이트
					adjList[i][j] = Math.min(adjList[i][j], adjList[i][k] + adjList[k][j]);
				}
			}
		}
	}

}
