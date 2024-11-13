package P071_구간합구하기3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static long[] nums;
	static long[] tree;
	static int S;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/P071_구간합구하기3/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		S = 1;

		// N보다 큰 S를 만듦
		while (S < N) {
			S *= 2;
		}

		// tree 만듦
		tree = new long[S * 2];
		
		// nums 만들고 값 채워 넣음
		nums = new long[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		// tree를 생성
		init();
		
		// 명령어를 받기 위한 변수 선언
		int a = 0, b = 0, c = 0;
		long d = 0;
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			
			// a = 1이라면 b를 c로 바꾼다.
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				updateBU(b, d);
			}
			// a = 2라면 b부터 c까지의 합을 출력한다.
			if(a == 2) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				System.out.println(query(1, S, 1, b, c));
			}
			
		}

	}

	static void init() {
		// Bottom-Up으로 구현
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i];
		}
		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		// Top-down 방식
		// 연관없음
		if (queryRight < left || right < queryLeft) {
			// 무시 -> 영향 없는 값
			return 0;
		}
		// 판단가능
		else if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		// 판단 불가
		else {
			int mid = (left + right) / 2;
			// 왼쪽 자식 호출 // 오른쪽 자식 호출 // 사이 로직은 문제에서 무엇을 요구했는지에 따라 다르다.
			return query(left, mid, node * 2, queryLeft, queryRight)
					+ query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

	static void update(int left, int right, int node, int target, long diff) {
		// Top-down 방식
		if (target < left || right < target) {
			return;
		} else {
			tree[node] += diff;
			// 리프 판단
			if (left != right) {
				int mid = (left + right) / 2;
				// 왼쪽 갱신
				update(left, mid, node * 2, target, diff);
				// 오른쪽 갱신
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	static long queryBU(int queryLeft, int queryRight) {
		int left = S + queryLeft - 1; // nodeLeft
		int right = S + queryRight - 1; // nodeRight
		long sum = 0;
		// 오직 왼쪽과 오른쪽이 완전히 교차했을 때 종료
		// 이 문제는 그림을 그려서 판단하자.
		while(left <= right) {
			// 둘이 같을 때는 둘 중 하나를 타고 뒤집히게 됨 -> 다음에 탈출
			if(left % 2 == 1) {
				sum += tree[left++];
			}
			if(right % 2 == 0) {
				sum += tree[right--];
			}
			// 위로 상승 -> 이 둘이 뒤집힐 때까지 상승
			left /= 2;
			right /= 2;
		}
		return sum;
	}
	
	static void updateBU(int target, long value) {
		int node = S + target - 1;
		tree[node] = value;
		node /= 2;
		while(node > 0) {
			// 루트에 도달할 때까지 올라감
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
	
}
