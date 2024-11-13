package P14565;

import java.io.*;
import java.util.*;

public class Main {

	static long N, A;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P14565/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 A를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		A = Long.parseLong(st.nextToken());
		
		// 덧셈 역원 계산
		long invSum = eSum();
		long invMul = eMul();
		
		System.out.println(invSum + " " + invMul);

	}
	
	static long eSum() {
		// 덧셈의 역원 식
		// A + 1*X = N*Y
		// 1*(-X) + N*(Y) = A
		// 1S + NT = R Q로 계산하면 되는 거임;;
		// euclid 함수는 a, b, c를 받음 -> ax + by = c 식에서
		EGCD res1 = euclid(1, N, A);
		
		// 일단 A와 r1 = d의 mod가 0인지 확인 -> 안되면 바로 -1반환
		if(A % res1.r != 0) return -1;
		
		// 초기해를 구함
		long x0 = res1.s * (A / res1.r);
		long y0 = res1.t * (A / res1.r);
		
		// 일반해 공식으로 알맞은 k를 구함
		// -(n-1) <= X <= 0 -> -(n-1) <= x0 + (b/d)*K <= 0
		// -(n-1) - x0 <= (b/d) * k <= -x0
		// (-(n-1) - x0) * (d/b) <= k <= -x0 * (d/b)
		// y >= 0
		// y0 - (a/d)*k >= 0
		// -(a/d) * k >= -y0
		// k <= (a/d)*(y0)
		// upper bound로 k 구한 후 lower bound로 검증
		
		long upperX2K = (long) (Math.ceil((-1) * x0 * (res1.r) / (double)N) - 1);
		long upperY2K = (long) (Math.ceil(1 / (double)res1.r * y0) - 1);
		// 둘 중 작은 값을 취한다.
		long upperK = Math.min(upperX2K, upperY2K);
		
		// 이 k 값이 lowerbound에서 만족하는지 확인
		long lowerK = (long) Math.ceil(((-1 * (N-1)) - x0) * (res1.r / (double)N)) - 1;
		if(lowerK > upperK) return -1;
		else {
			// k를 이용해 X를 구한다. -> 우리가 원하는 값
			// 근데 여기 x는 -1 곱해진 x임 -> 결과는 다시 -1 곱해야 함
			return (-1) * (x0 + (N / res1.r) * upperK);
		}
		
	}
	
	static long eMul() {
		// 곱셈의 역원의 식
		// AX + N(-Y) = 1
		// 일단 해를 구함
		// euclid 함수는 a, b, c로 넣어야 함
		EGCD res2 = euclid(A, N, 1);
		
		// 만약 c % d != 0 이라면 해 없음
		if(1 % res2.r != 0) return -1;
		
		// 초기해를 구함
		long x0 = res2.s * (1/res2.r);
		long y0 = res2.t * (1/res2.r);
		
		// k 범위를 구함
		// 0 <= x0 + (b/d)*K <= N - 1
		// -x0 * (d/b) <= K <= ((N-1) - x0)*(d/b)
		// y0 - (a/d)*K <= 0
		// (a/d)*k >= y0
		// k >= (d/a) * y0
		// lower의 k를 구한뒤 upper에서 검증
		long lowerX2K = (long) Math.ceil((-1)*x0*(res2.r/(double)N)) - 1;
		
		long lowerY2K = (long) Math.ceil((res2.r/(double)A * y0)) - 1;
		// 둘 중 큰 값을 고름
		long lowerK = Math.max(lowerX2K, lowerY2K);
		// upper 구함
		long upperK = (long) Math.ceil(((N - 1) - x0) * (res2.r / (double)N)) - 1;
		
		// 만약 lowerK가 더 크면,해 없음
		if(lowerK > upperK) return -1;
		else {
			// 우리가 원하는 해는 X임
			return x0 + (N / res2.r) * upperK;
		}
	}
	
	static EGCD euclid(long a, long b, long c) {
		// 초기 s1과 t1, r1 설정
		long s1 = 1, t1 = 0, r1 = a;
		long s0 = 0, t0 = 1, r0 = b;
		long temp;
		// r0가 0이 될 때까지
		while(r0 != 0) {
			
			// 먼저 q를 구한다.
			long q = r1 / r0;
			
			// r부터 업데이트
			temp = r1 - q * r0;
			r1 = r0;
			r0 = temp;
			
			// t 업데이트
			temp = t1 - q * t0;
			t1 = t0;
			t0 = temp;
			
			// s 업데이트
			temp = s1 - q * s0;
			s1 = s0;
			s0 = temp;
		}
		
		// 이 때의 gcd는 0 바로 위니까 r1 쪽을 반환
		return new EGCD(s1, t1, r1);
	}
	
	static class EGCD {
		long s, t, r;
		
		public EGCD (long s, long t, long r) {
			this.s = s;
			this.t = t;
			this.r = r;
		}
	}

}
