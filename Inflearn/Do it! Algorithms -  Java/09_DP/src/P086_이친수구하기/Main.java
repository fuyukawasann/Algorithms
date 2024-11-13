package P086_이친수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P086_이친수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// N 자리를 입력 받는다.
		int N = Integer.parseInt(br.readLine());
		
		// DP[i][0] : i 자리에서 0으로 끝나는 수
		// DP[i][1] : i 자리에서 1로 끝나는 수
		long DP[][] = new long[N + 1][2];
		DP[1][1] = 1; // 1자리 이친수는 1개
		DP[1][0] = 0; // 0으로 시작하는 이친수는 규칙에 위배
		
		for(int i = 2; i <= N; i++) {
			DP[i][0] = DP[i-1][1] + DP[i - 1][0];
			DP[i][1] = DP[i - 1][0];
		}
		
		System.out.println(DP[N][0] + DP[N][1]);
		

	}

}
