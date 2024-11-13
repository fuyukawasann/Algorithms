package P14438;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, S;
	
	static int[] nums;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P14438/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 트리 생성
		S = 1;
		while( S <= N) {
			S <<= 1;
		}
		
		tree = new int[2*S];
		
		init();
		
		// 쿼리 실행
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if(command == 1) {
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				update(i, v);
			}
			else {
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, S, 1, i, j)).append("\n");
			}
		}
		
		// 정답을 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	static void init() {
		for(int i = 0; i < S; i++) {
			if(i < N) tree[S + i] = nums[i];
			else tree[S + i] = Integer.MAX_VALUE;
		}
		
		for(int i = S - 1; i >= 1; i--) {
			tree[i] = Math.min(tree[i*2], tree[i*2+1]);
		}
	}
	
	static int query(int left, int right, int node, int qL, int qR) {
		if(right < qL || qR < left) {
			return Integer.MAX_VALUE;
		} else if(qL <= left && right <= qR) {
			return tree[node];
		} else {
			int mid = (left + right) / 2;
			
			return Math.min(query(left, mid, node * 2, qL, qR), query(mid + 1, right, node * 2 + 1, qL, qR));
		}
	}
	
	static void update(int target, int value) {
		int node = target + S - 1;
		tree[node] = value;
		node /= 2;
		
		while(node > 0) {
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
			
			node /= 2;
		}
		
	}

}
