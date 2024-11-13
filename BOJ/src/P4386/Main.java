package P4386;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	
	static int[] parent;
	static Star[] stars;
	static InterStella[] edgeList;
	
	
	static class InterStella implements Comparable<InterStella> {
		int from;
		int to;
		float cost;
		
		public InterStella(int from, int to, float cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(InterStella o) {
			// TODO Auto-generated method stub
			return Float.compare(this.cost, o.cost);
		}
	}
	
	static class Star {
		float x;
		float y;
		
		public Star (float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// 라이브러리 불러오기
		System.setIn(new FileInputStream("src/P4386/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 별의 정보를 받는다.
		// 별의 개수를 받는다.
		V = Integer.parseInt(br.readLine());
		
		// 배열을 선언
		parent = new int[V];
		stars = new Star[V];
		
		// 부모 배열 초기화
		init();
		
		// 별 정보 받기
		float x, y;
		for(int s = 0; s < V; s++) {
			st = new StringTokenizer(br.readLine());
			x = Float.parseFloat(st.nextToken());
			y = Float.parseFloat(st.nextToken());
			
			// 별 정보 넣기
			stars[s] = new Star(x, y);
		}
		
		// edge 만들기
		E = V*(V-1)/2;
		edgeList = new InterStella[E];
		int idx = 0;
		for(int i = 0; i < V; i++) {
			for(int j = i+1; j < V; j++) {
				// cost 계산
				float xsquare = (float)Math.pow(stars[i].x-stars[j].x, 2);
				float ysquare = (float)Math.pow(stars[i].y-stars[j].y, 2);
				float cost = (float)Math.sqrt(xsquare+ysquare);
				
				edgeList[idx] = new InterStella(i, j, cost);
				idx++;
			}
		}
		
		// 엣지를 이용해서 별자리 최소 비용을 만듦
		StringBuilder sb = new StringBuilder();
		sb.append(Kruskal()).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	static float Kruskal() {
		//변수 선언
		float mstCost = 0;
		int edgeCount = 0;
		
		// 정렬
		Arrays.sort(edgeList);
		
		for(int i = 0; i < E; i++) {
			InterStella current = edgeList[i];
			
			if(find(current.from) == find(current.to)) continue;
			
			// union
			union(current.from, current.to);
			
			// cost update
			mstCost += current.cost;
			edgeCount++;
			
			// break if we have V-1 edge
			if(edgeCount == V-1) {
				return mstCost;
			}
		}
		
		
		// If we don't get V-1 edges, then return -1
		return -1;
	}
	
	static void init() {
		for(int i = 0; i <V; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) parent[pB] = pA;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
