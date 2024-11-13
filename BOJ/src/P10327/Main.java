package P10327;

import java.io.*;
import java.util.*;

public class Main {
	
	static long[] Fibo;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P10327/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 피보나치 수열을 먼저 구한다.
		Fibo = new long[49];
		Fibo[1] = 1;
		Fibo[2] = 1;
		
		fibonacci(46);
		
		while(T-- > 0) {
			// 테스트 케이스 실행
			int N = Integer.parseInt(br.readLine());
			
			// 유클리드 호제법으로 구한다.
			long[] answer = solve(3, 1, N-1, N);
			System.out.println(answer[0] + " " + answer[1]);
		}
		
	}
	
	static long[] solve(int start, long beforeA, long beforeB, int N) {
		// X + Y = N으로 구할 건데 maxK로 계산해야 함
		// 만약 유클리드 호제법을 계산했는데 C % D == 0이 아니면 더 이상으로 줄일 수 없는 것임;;
		// 재귀적으로 계산할 것임
		// A, B가 헷갈리니까 a, b 미리 정함
		// 일반식 G(n) = F(n-2)G(1) + F(n-1)G(2)로 구할 거임;;
		// Fibo[start - 2] * G(1) + Fibo[start - 1] * G(2) = N
		// Fibo[start - 2] * X + Fibo[start - 1] * Y = N
		// a = Fibo[start - 2], b = Fibo[start - 1]
		long a = Fibo[start - 2];
		long b = Fibo[start - 1];
		EGCD res = uclid(a, b, N);
		
		// C % D가 0이 안되면 beforeA, beforeB를 리턴
		if(N % res.r != 0) return new long[] {beforeA, beforeB};
		
		// 초기해 구하기
		// x0 = s0 * (c / d);
		long x0 = res.s * (N / res.r);
		// y0 = t0 * (c / d);
		long y0 = res.t * (N / res.r);
		
		// 일반해
		// x = x0 + (b/d) * k
		// y = y0 - (a /d ) * k
		// 1 <= x <= N - 1
		// 1 <= y <= N - 1
		// x <= y 조건도 있음
		
		// 1 <= x0 + b/d * k <= N - 1
		// (1 - x0) * d/b <= k <= (N - 1 - x0) * d/b
		
		// 1 <= y0 - (a / d) * k <= N - 1
		// (y0 + 1 - N) * d/a <= k <= (y0 - 1) * d/a
		
		// x0 + (b / d)* k <= y0 - (a / d) * k
		// (a + b)/ d * k <= y0 - x0
		// k <= (y0 - x0) * d / (A + B);
		
		// uppper bound
		long upperX2K = (long) Math.floor((N - 1 - x0) * res.r / (double)b);
		long upperY2K = (long) Math.floor((y0 - 1) * res.r / (double)a);
		long upperMK = (long) Math.floor((y0 - x0) * res.r / (double)(a + b));
		// 둘 중 작은 값 사용
		long upperK = Math.min(Math.min(upperX2K, upperY2K), upperMK);
		
		// lower bound
		long lowerX2K = (long) Math.ceil((1 - x0) * res.r / (double)b);
		long lowerY2K = (long) Math.ceil((y0 + 1 - N) * res.r / (double)a);
		// 둘 중 큰 값을 사용
		long lowerK = Math.max(lowerX2K, lowerY2K);
		
		// 검증
		if(lowerK > upperK) return new long[] {beforeA, beforeB};
		else {
			// upperK로 계산할 거임
			// x = x0 + 1/res.r * k, y = y0 - 1/res.r * k
			// 넣을 때 beforeA = x, beforeB = y 그리고 N은 x임
			long x = x0 + b/res.r * upperK;
			long y = y0 - a/res.r * upperK;
			
			return solve(start+1, x, y, N);
		}
		
	}
	
	static EGCD uclid(long a, long b, long c) {
		long s1 = 1, t1 = 0, r1 = a;
		long s0 = 0, t0 = 1, r0 = b;
		long temp;
		
		while(r0 != 0) {
			
			long q = r1/r0;
			
			temp = r1 - q * r0;
			r1 = r0;
			r0 = temp;
			
			temp = t1 - q * t0;
			t1 = t0;
			t0 = temp;
			
			temp = s1 - q * s0;
			s1 = s0;
			s0 = temp;
		}
		
		return new EGCD(s1, t1, r1);
	}
	
	static void fibonacci(int n) {
		// bottom-up으로 구할 거임!!
		int i = 3;
		while(i <= n) {
			Fibo[i] = Fibo[i - 2] + Fibo[i - 1];
			i++;
		}
	}
	
	static class EGCD {
		long s, t, r;
		
		public EGCD(long s, long t, long r) {
			this.s = s;
			this.t = t;
			this.r = r;
		}
	}

}
