package P11658;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, S;
	
	static int[][] nums;
	static int[][] tree;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P11658/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// num 받기
		nums = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// tree 생성
		S = 1;
		while (S <= N) {
			S <<= 1;
		}
		tree = new int[N+1][2*S];
		init();
		
		// Run Query
		StringBuilder sb = new StringBuilder();
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			if(w == 0) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update(x, y, c);
			}
			else {
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int result = 0;
				sb.append(query(1, S, 1, y1, y2, x1, x2)).append("\n");
			}
		}
		
		// Print out the answer
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void init() {
		for(int j = 1; j <= N; j++) {
			for(int i = 0; i < N; i++) {
				tree[j][S + i] = nums[j - 1][i];
			}
		}
		
		for(int j = 1; j <= N; j++) {
			for(int i = S - 1; i >= 1; i--) {
				tree[j][i] = tree[j][i*2] + tree[j][i*2+1];
			}
		}
	}
	
	static int query(int left, int right, int node, int qL, int qR, int tL, int tR) {
		if(right < qL || qR < left) return 0;
		else if(qL <= left && right <= qR) {
			int res = 0;
			for(int i = tL; i <= tR; i++) {
				res += tree[i][node];
			}
			return res;
		}
		else {
			int mid = (left + right) / 2;
			return (query(left, mid, node * 2, qL, qR, tL, tR) +
					query(mid + 1, right, node * 2 + 1, qL, qR, tL, tR));
		}
	}
	
	static void update(int whichTree, int target, int value) {
		int node = target + S - 1;
		tree[whichTree][node] = value;
		node /= 2;
		
		while(node > 0) {
			tree[whichTree][node] = tree[whichTree][node * 2] + tree[whichTree][node * 2 + 1];
			node /= 2;
		}
	}

}
