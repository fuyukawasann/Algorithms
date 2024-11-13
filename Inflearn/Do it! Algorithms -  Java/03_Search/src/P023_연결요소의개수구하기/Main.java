package P023_연결요소의개수구하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	
	static int[] parent;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P023_연결요소의개수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		// 초기화
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		// 간선을 받고 인접 리스트를 만듦
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 들어올 때마다 union연산을 함
			union(u, v);
		}
		
		// parent 배열을 확인해서 다른 값들의 개수를 확인한다.
		int count = 0;
		HashSet<Integer> hash = new HashSet<Integer>();
		for(int i = 1; i <= N; i++) {
			if(!hash.contains(find(i))) {
				hash.add(parent[i]);
				count++;
			}
		}
		
		// count를 출력한다.
		System.out.println(count);

	}
	
	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parent[pA] = parent[pB];
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
