package P032_동전개수의최솟값구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P032_동전개수의최솟값구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		// 뒤에서부터 확인하면서 푼다.
		int count = 0;
		for(int i = N - 1; i >= 0; i--) {
			// 만약 K가 0이라면 종료
			if(K == 0) break;
			// K보다 지금 값이 크다 -> continue
			if(K < A[i]) continue;
			// 아니면 나눗셈의 몫 -> count, modular로 업데이트
			count += (K / A[i]);
			K %= A[i];
		}
		
		System.out.println(count);

	}

}
