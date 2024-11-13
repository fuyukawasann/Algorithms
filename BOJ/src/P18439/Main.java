package P18439;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, S;
	
	static int[] nums;
	static Node[] tree;
	
	static class Node {
		int numEven, numOdd;
		
		public Node(int numEven, int numOdd) {
			this.numEven = numEven;
			this.numOdd = numOdd;
		}
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P18439/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 입력 받는다.
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// Make Tree
		S = 1;
		while (S <= N) {
			S <<= 1;
		}
		tree = new Node[2*S];
		init();
		
		// Run Query
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if(command == 1) {
				int i = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				update(i, x);
			} else if(command == 2) {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, S, 1, l, r).numEven).append("\n");
			} else {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, S, 1, l, r).numOdd).append("\n");
			}
		}
		
		// Print out the answer
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void init() {
		for(int i = 0; i < S; i++) {
			if(i < N) {
				if(nums[i] % 2 == 0) {
					tree[S + i] = new Node(1, 0);
				}
				else tree[S + i] = new Node(0, 1);
			}
			else {
				tree[S + i] = new Node(0, 0);
			}
		}
		
		for(int i = S - 1; i >= 1; i--) {
			tree[i] = new Node(tree[i*2].numEven + tree[i*2+1].numEven,
					tree[i*2].numOdd + tree[i*2+1].numOdd);
		}
	}
	
	static Node query(int left, int right, int node, int qL, int qR) {
		if(right < qL || qR < left) {
			return new Node(0, 0);
		} else if(qL <= left && right <= qR) {
			return tree[node];
		} else {
			int mid = (left + right) / 2;
			Node leftN = query(left, mid, node * 2, qL, qR);
			Node rightN = query(mid + 1, right, node * 2 + 1, qL, qR);
			
			return new Node(leftN.numEven + rightN.numEven, leftN.numOdd + rightN.numOdd);
		}
	}
	
	static void update(int target, int value) {
		int node = target + S - 1;
		if(value % 2 == 0) {
			tree[node].numEven = 1;
			tree[node].numOdd = 0;
		}
		else {
			tree[node].numEven = 0;
			tree[node].numOdd = 1;
		}
		node /= 2;
		
		while(node > 0) {
			tree[node].numEven = tree[node * 2].numEven + tree[node * 2 + 1].numEven;
			tree[node].numOdd = tree[node * 2].numOdd + tree[node * 2 + 1].numOdd;
			node /= 2;
		}
	}

}
