package P2129;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	
	static int N, M, S, T;
	
	static City[] dist;
	static ArrayList<Road> roadList;
	static int[] minCost;
	
	static class City {
		int cost, tired;
		
		public City(int cost, int tired) {
			this.cost = cost;
			this.tired = tired;
		}
	}
	
	static class Road {
		int from, to, cost, tired;
		
		public Road(int from, int to, int cost, int tired) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			this.tired = tired;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 파일 입출력 설정
		System.setIn(new FileInputStream("src/P2129/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M, S, T
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 배열 생성
		dist = new City[N];
		roadList = new ArrayList<>();
		minCost = new int[N];
		
		// 배열 초기화
		
		for(int i = 0; i < N; i++) {
			dist[i] = new City(INF, INF);
			minCost[i] = INF;
		}
		
		int u, v, a, c, b;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			// u -> v로 갈 때
			// 피로도 최소를 계속 업데이트
			roadList.add(new Road(u, v, c, a));
			minCost[u] = Math.min(minCost[u], a);
			roadList.add(new Road(v, u, c, b));
			minCost[v] = Math.min(minCost[v], b);
		}
		
		// 벨만-포드로 검정
		boolean isNegativeCycle = bellmanFord(S);
		// 최적해가 없다면
		if(dist[T].tired == INF) {
			System.out.println("VOID");
		}
		else {
			// 최적해는 있지만 음의 사이클 이라면
			if(isNegativeCycle) {
				System.out.println("UNBOUND");
			}
			// 음의 사이클이 없고 최적해가 있다면
			else {
				System.out.println(dist[T].tired + " " + dist[T].cost);
			}
		}
		
	}
	
	static boolean bellmanFord(int start) {
		// 시작점의 dist를 초기화
		dist[start].cost = 0;
		dist[start].tired = 0;
		
		// V-1번 만큼 간선 리스트를 순회
		for(int i = 1; i <= N-1; i++) {
			for(Road r : roadList) {
				// 아직 갈 곳의 dist가 INF라면 continue
				if(dist[r.from].tired == INF) continue;
				
				// minCost와 동일하지 않으면 해당 간선은 이용하지 않음
				if(r.tired != minCost[r.from]) continue;
				
				// 피로도가 같다면 거리가 작을 때 거리를 갱신
				if(dist[r.to].tired == dist[r.from].tired + r.tired) {
					if(dist[r.to].cost > dist[r.from].cost + r.cost) {
						dist[r.to].cost = dist[r.from].cost + r.cost;
					}
				}
				// 피로도가 기존 피로도보다 작다면 갱신
				if(dist[r.to].tired > dist[r.from].tired + r.tired) {
					dist[r.to].tired = dist[r.from].tired + r.tired;
					dist[r.to].cost = dist[r.from].cost + r.cost;
				}
			}
		}
		
		boolean isNegativeCycle = false;
		for(Road r : roadList) {
			if(dist[r.from].tired == INF) continue;
			
			if(r.tired != minCost[r.from]) continue;
			
			if(dist[r.to].tired > dist[r.from].tired + r.tired) {
				if(r.to == T) {
					isNegativeCycle = true;
					break;
				}
			}
		}
		
		
		return isNegativeCycle;
	}

}
