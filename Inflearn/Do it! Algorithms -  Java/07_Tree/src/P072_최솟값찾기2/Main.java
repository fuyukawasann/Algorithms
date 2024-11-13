package P072_최솟값찾기2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 1_000_000_001;
	
	static int N, M, S;
	static int[] nums;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 기본 라이브러리 선언
		System.setIn(new FileInputStream("src/P072_최솟값찾기2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N과 M을 읽어온다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// tree를 생성하기 위해 S를 생성
		S = 1;
		while(S <= N) {
			S = S << 1;
		}
		
		// 배열 생성
		nums = new int[N];
		tree = new int[2*S];
		
		// nums 생성
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 트리 생성
		init();
		
		// 쿼리 실행
		// 정답 저장을 위한 스트링빌더 선언
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(query(1, S, 1, a, b)).append("\n");
		}
		
		// 정답 출력
		// 하나 더 붙은 엔터 제거
		sb.deleteCharAt(sb.length() - 1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void init() {
		// S ~ 2S - 1까지 생성 (리프 노드)
		for(int i = 0; i < S; i++) {
			if(i < N) {
				tree[S + i] = nums[i];
			}
			else {
				tree[S+i] = INF;
			}
		}
		
		// 내부 노드 선언
		for(int i = S - 1; i >= 1; i--) {
			tree[i] = Math.min(tree[i*2], tree[i*2+1]);
		}
	}
	
	static int query(int left, int right, int node, int qL, int qR) {
		if(right < qL || qR < left) {
			return INF;
		}
		else if(qL <= left && right <= qR) {
			return tree[node];
		}
		else {
			int mid = (left + right) / 2;
			return Math.min(query(left, mid, node*2, qL, qR), query(mid+1, right, node*2+1, qL, qR));
		}
	}

}
