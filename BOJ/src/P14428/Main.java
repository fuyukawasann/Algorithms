package P14428;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, S;
	static int[] nums;
	static Node[] tree;
	
	static class Node {
		int idx;
		int value;
		
		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P14428/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// Index Tree
		S = 1;
		while(S <= N) {
			S <<= 1;
		}
		
		tree = new Node[2*S];
		
		init();
		
		// 쿼리 실행
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			// 업데이트 작업
			if(command == 1) {
				int it = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				update(it, val);
			}
			else {
				int it = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, S, 1, it, j).idx).append("\n");
			}
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static void init() {
		for(int i = 0; i < S; i++) {
			if(i < N) {
				tree[S + i] = new Node(i+1, nums[i]);
			}
			else {
				tree[S + i] = new Node(i+1, Integer.MAX_VALUE);
			}
		}
		
		for(int i = S - 1; i >= 1; i--) {
			// 먼저 좌, 우의 값을 확인해서 left child가 더 작으면 Left child의 값을 취함 (둘의 value가 같을 때도 왼쪽 자식을 취함)
			if(tree[i * 2].value <= tree[i*2+1].value) {
				tree[i] = new Node(tree[i*2].idx, tree[i*2].value);
			}
			// 오른쪽 자식이 더 작으면 오른쪽 자식을 취함
			else {
				tree[i] = new Node(tree[i*2+1].idx, tree[i*2+1].value);
			}
		}
}
	
	static Node query(int left, int right, int node, int qL, int qR) {
		if(right < qL || qR < left) {
			return new Node(0, Integer.MAX_VALUE);
		}
		
		else if(qL <= left && right <= qR) {
			return tree[node];
		}
		else {
			int mid = (left + right) / 2;
			Node leftNode = query(left, mid, node * 2, qL, qR);
			Node rightNode = query(mid + 1, right, node * 2 + 1, qL, qR);
			
			// 만약 left값이 더 작거나 같으면 left를 반환
			if(leftNode.value <= rightNode.value) {
				return leftNode;
			}
			else return rightNode;
			
		}
	}
	
	static void update(int target, int value) {
		int node = target + S - 1;
		tree[node].value = value;
		node /= 2;
		
		while(node > 0) {
			// 왼쪽 자식과 오른쪽 자식을 비교
			if(tree[node * 2].value <= tree[node * 2 + 1].value) {
				tree[node].idx = tree[node*2].idx;
				tree[node].value = tree[node*2].value;
			}
			else {
				tree[node].idx = tree[node*2+1].idx;
				tree[node].value = tree[node*2+1].value;
			}
			
			node /= 2;
		}
		
	}

}
