package P051_여행계획짜기;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	static int[] parent;

	static class Edge {
		int a;
		int b;

		public Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P051_여행계획짜기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// 여행 계획을 받는다.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (i >= j)
					continue;

				if (a == 1) {
					union(i, j);
				}
			}
		}

		// 여행
		st = new StringTokenizer(br.readLine());
		int before = 0, now = 0;
		boolean isPossible = true;
		for (int i = 1; i <= M; i++) {
			now = Integer.parseInt(st.nextToken());
			if (i == 1) {
				before = now;
				continue;
			} else {
				if (find(before) == find(now)) {
					before = now;
					continue;
				} else {
					isPossible = false;
					break;
				}
			}

		}

		// 결과 출력
		if (!isPossible)
			System.out.println("NO");
		else
			System.out.println("YES");

	}

	static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);

		if (pA != pB) {
			if (pA < pB) {
				parent[pB] = pA;
			} else
				parent[pA] = pB;
		}
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

}
