//package P16993;
//
//import java.io.*;
//import java.util.*;
//
//public class Main {
//	
//	static int N, M, S;
//	
//	static int[] nums;
//	static int[] tree;
//	
//	static PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/P16993/input.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		
//		// N 받기
//		N = Integer.parseInt(br.readLine());
//		nums = new int[N];
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i = 0; i < N; i++) {
//			nums[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		// Make Trere
//		S = 1;
//		while(S < N) {
//			S <<= 1;
//		}
//		tree = new int[2*S];
//		init();
//		
//		// Query
//		M = Integer.parseInt(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		for(int m = 1; m <= M; m++) {
//			st = new StringTokenizer(br.readLine());
//			int i = Integer.parseInt(st.nextToken());
//			int j = Integer.parseInt(st.nextToken());
//			
//			query(1, S, 1, i, j);
//			sb.append(pq.poll()).append("\n");
//		}
//		
//		bw.write(sb.toString());
//		bw.flush();
//		bw.close();
//		br.close();
//
//	}
//	
//	static void init() {
//		for(int i = 0; i < N; i++) {
//			tree[S + i] = nums[i];
//		}
//		
//		for(int i = S - 1; i > 0; i--) {
//			tree[i] = tree[i*2] + tree[i*2+1];
//		}
//	}
//	
//	static int query(int left, int right, int node, int qL, int qR) {
//		if(right < qL || qR < left) {
//			return 0;
//		}
//		else {
//			// 리프 이면 pq에 내 값을 넣고 ret
//			if(left == right) {
//				pq.offer(tree[node]);
//				// 만약 내 idx가 1이다. 그냥 내 값을 반환
//				// 만약 내 idx가 N이다. 그냥 내 값을 반환
//				if(left == 1 || left == N) return tree[node];
//				// 내 노드가 짝수 노드이고 내 right < qR 인 경우만, 오른쪽 노드와 더한 것도 추가
//				if(left % 2 == 0 && right < qR) pq.offer(tree[node] + tree[node + 1]);
//				// 내 노드가 홀수 노드이고 내 left > qL 인 경우만, 왼쪽 노드와 더한 것도 추가
//				if(left % 2 == 1 && left > qL) pq.offer(tree[node - 1] + tree[node + 1]);
//
//				return tree[node];
//			}
//			// 아니면 자식을 부름;;
//			int mid = (left + right) / 2;
//			int leftChild = query(left, mid, node * 2, qL, qR);
//			int rightChild = query(mid + 1, right, node * 2 + 1, qL, qR);
//			
//			
//		}
//	}
//
//}
