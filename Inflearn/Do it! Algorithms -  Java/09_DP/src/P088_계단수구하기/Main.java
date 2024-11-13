package P088_계단수구하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static final long MOD = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P088_계단수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// DP 선언
		int DP[][] = new int[N + 1][10];
		
		// 첫째 자리는 임의로 입력한다.
		// 첫째 자리
		DP[1][0] = 0;
		for(int i = 1; i <= 9; i++) DP[1][i] = 1;
		
		// 둘째 자리부터는 점화식으로 해결한다.
		// 자리수
		for(int i = 2; i <= N; i++) {
			// 올 수 있는 숫자
			for(int j = 0; j <= 9; j++) {
				// 0일 때
				if(j == 0) DP[i][j] = DP[i - 1][1];
				// 9일 때
				else if(j == 9) DP[i][j] = DP[i - 1][8];
				// 나머지
				else {
					DP[i][j] =(int)(((long)DP[i - 1][j - 1] + (long)DP[i - 1][j + 1]) % MOD);
				}
			}
		}
		
		// 정답을 합해서 출력
		long result = 0;
		for(int i = 0; i <= 9; i++) {
			result = (result + (long)DP[N][i]) % MOD;
		}
		
		System.out.println(result);
	}

}
