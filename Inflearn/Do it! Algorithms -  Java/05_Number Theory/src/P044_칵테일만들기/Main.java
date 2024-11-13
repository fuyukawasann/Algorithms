package P044_칵테일만들기;

import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Node>[] A;
	static long lcm;
	static boolean[] visited;
	static long[] D;
	
	static class Node {
		int b, p, q;
		
		public Node(int b, int p, int q) {
			this.b = b;
			this.p = p;
			this.q = q;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P044_칵테일만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		A = new ArrayList[N];
		visited = new boolean[N];
		D = new long[N];
		lcm = 1;
		for(int i = 0; i < N; i++) {
			A[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			A[a].add(new Node(b, p, q));
			A[b].add(new Node(a, q, p));
			lcm *= (p * q / gcd(p, q));
		}
		
		D[0] = lcm;
		DFS(0);
		long mgcd = D[0];
		
		for(int i = 1; i < N; i++) {
			mgcd = gcd(mgcd, D[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(D[i] / mgcd).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static long gcd(long a, long b) {
		if(b == 0) {
			return a;
		}
		else return gcd(b, a % b);
	}
	
	static void DFS(int Node) {
		visited[Node] = true;
		
		for(Node i : A[Node]) {
			int next = i.b;
			if(!visited[next]) {
				D[next] = D[Node] * i.q / i.p;
				DFS(next);
			}
		}
	}

}
