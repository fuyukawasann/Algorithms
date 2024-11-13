package P11780;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final long INF = 10000000001L; 
	static int N, M;
	
	static long[][] dist;
	static ArrayList<Integer>[][] directory;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P11780/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// dist
		dist = new long[N+1][N+1];
		directory = new ArrayList[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) dist[i][j] = 0;
				else dist[i][j] = INF;
				directory[i][j] = new ArrayList<>();
			}
		}
		
		// 도시 간 버스
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		// 플로이드-워샬 실행
		floydWarshall();
		
		// dist를 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		// 경로 출력
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if (i == j) {
					sb.append(0).append("\n");
					continue;
				}
				else {
					sb.append(2 + directory[i][j].size()).append(" ");
					sb.append(i).append(" ");
					for(Integer element : directory[i][j]) {
						sb.append(element).append(" ");
					}
					sb.append(j).append("\n");
				}
			}
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void floydWarshall() {
		for(int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(dist[i][k] == INF || dist[k][j] == INF) continue;
					
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						
						// Array 비우기
						directory[i][j].clear();
						// 만약 directory[i][k]가 비어있다 -> 그냥 directory[i][j]에 k 추가
						if(directory[i][k].isEmpty()) {
							directory[i][j].add(k);
						}
						// directory가 안 비어 있다. -> directory 안 내용물을 dir[i][j]에 추가 후 k 추
						else {
							for(Integer element : directory[i][k]) {
								directory[i][j].add(element);
							}
							directory[i][j].add(k);
							for(Integer element : directory[k][j]) {
								directory[i][j].add(element);
							}
						}
					}
					
				}
			}
		}
	}

}
