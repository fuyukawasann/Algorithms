package P1849;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, S;
	
	static int[] nums;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1849/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		
		// Create tree and initialized
		S = 1;
		while(S < N) {
			S <<= 1;
		}
		tree = new int[2*S];
		init();
		
		// A[i]를 받음
		for(int i = 1; i <= N; i++) {
			int idx = query(1, S, 1, Integer.parseInt(br.readLine()) + 1);
			// i를 nums[idx]에 넣고 트리를 업데이트
			nums[idx] = i;
			update(idx);
		}
		
		// 답을 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(nums[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void init() {
		for(int i = 0; i < N; i++) {
			tree[S + i] = 1;
		}
		
		for(int i = S - 1; i > 0; i--) {
			tree[i] = tree[i*2] + tree[i*2+1];
		}
	}
	
	static int query(int left, int right, int node, int target) {
		// 만약 타깃이 더 크다면 -1을 반
		if(target > tree[node]) {
			return -1;
		}
		// 그렇지 않다면 자식에게 판단을 맡김
		else {
			// 만약 리프 노드라면 left를 반환
			if(left == right) {
				if(tree[node] != 0) return left;
				else return -1;
			}
			
			// 그렇지 않다면...
			int mid = (left + right) / 2;
			int leftVal = query(left, mid, node * 2, target);
			// 만약 leftVal이 -1을 반환한다면, left 자식의 값만큼 target을 업데이트하고 오른쪽을 탐색
			if(leftVal == -1) {
				return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
			} else {
				return leftVal;
			}
		}
			
		
	}
	
	static void update(int target) {
		int node = target + S - 1;
		tree[node] = 0;
		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}

}
