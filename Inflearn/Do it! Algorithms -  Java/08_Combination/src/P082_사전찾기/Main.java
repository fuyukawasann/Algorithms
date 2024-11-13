package P082_사전찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int[][] dp = new int[201][201];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P082_사전찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();

		// N, M, K를 받는다.
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// Z바구니로 푼다.
		int total = N + M;
		// 만약, 사전에 수록되어 있는 문자열의 개수가 K보다 작을 경우,
		if (combination(N + M, M) < K) {
			// -1을 출력하고 탐색을 실시하지 않는다.
			sb.append(-1);
			sb.append('\n');
		}
		// 그렇지 않다면 탐색을 한다.
		else {
			int z = M; // Z 바구니 개수
			while (z != 0) {
				// 만약, z바구니 여유가 a가 들어갈 경우의 수보다 작거나 같은 경우,
				if(total == z) {
					sb.append('z');
					total--;
					z--;
				}
				else if (K <= combination(total - 1, z)) {
					// a를 넣고
					sb.append('a');
					// total(자릿수) 하나 낮추고 z 개수는 손대지 않는다.
					total--;
				}
				// 그렇지 않는 경우
				else {
					// z를 넣고
					sb.append('z');
					// total(자릿수) 하나 낮추고 z 개수를 하나 낮춘다.
					K -= combination(total - 1, z);
					total--;
					z--;
				}

			}
			if(total != 0) {
				for(int i = 0; i < total; i++) {
					sb.append('a');
				}
			}
			
			// 정답 출력용 공백 추가
			sb.append('\n');
			
		}
		// 정답 출력
		System.out.println(sb.toString());

	}

	public static int combination(int n, int r) {
		if (n == r || r == 0) {
			return 1;
		} else if (dp[n][r] != 0) {
			return dp[n][r];
		} else {
			return dp[n][r] = Math.min((int) 1e9, combination(n - 1, r - 1) + combination(n - 1, r));
		}
	}

}
