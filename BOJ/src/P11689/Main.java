package P11689;

import java.io.*;
import java.util.*;

public class Main {
	
	static long N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P11689/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		long result = N;
		long originalN = N;

		for(long i = 2; i * i <= N; i++) {
			// i가 N의 소인수인 경우
			if(N % i == 0) {
				// 해당 소인수로 N을 나누면서 모든 i의 배수들을 제거
				while(N % i == 0) {
					N /= i;
				}
				// 오일러 피 함수 업데이트
				result -= result / i;
			}
		}
		
		// 마지막으로 남은 소수 처리
		if(N > 1) {
			result -= result / N;
		}
		
		// 결과 출력
		System.out.println(result);
	}
}