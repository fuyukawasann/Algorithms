package P073_구간곱구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 나누는 수
	static final int DIV = 1_000_000_007;

	// 수의 개수 N, 수 변경 M 구간 곱 K
	static int N, M, K, S;
	static int[] nums;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P073_구간곱구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N, M, K를 받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// S를 생성
		S = 1;
		while (S <= N) {
			S = S << 1;
		}

		// 트리 생성
		tree = new int[2 * S];

		// 테스트 출력 S
		// System.out.println(S);

		// nums 배열 생성
		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		// 인덱스 트리를 생성
		init();
		
		// 답 연산 시작
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// update 연산인 경우
			if(a == 1) {
				update(b, c);
			}
			// query 연산인 경우
			else {
				sb.append(query(1, S, 1, b, c) % DIV).append("\n");
			}
		}
		
		// 답을 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	static void init() {
	    for (int i = 0; i < S; i++) {
	        if (i < N) {
	            tree[S + i] = nums[i];
	        } else {
	            tree[S + i] = 1;
	        }
	    }

	    for (int i = S - 1; i >= 1; i--) {
	        tree[i] = (int)(((long)tree[i * 2] * tree[i * 2 + 1]) % DIV);
	    }
	}

	static int query(int left, int right, int node, int qL, int qR) {
	    if (right < qL || qR < left) {
	        return 1;
	    } else if (qL <= left && right <= qR) {
	        return tree[node];
	    } else {
	        int mid = (left + right) / 2;
	        return (int)(((long)query(left, mid, node * 2, qL, qR) * query(mid + 1, right, node * 2 + 1, qL, qR)) % DIV);
	    }
	}

	static void update(int target, int value) {
	    int node = S + target - 1;
	    tree[node] = value;
	    node /= 2;

	    while (node != 0) {
	        tree[node] = (int)(((long)tree[node * 2] * tree[node * 2 + 1]) % DIV);
	        node /= 2;
	    }
	}


}
