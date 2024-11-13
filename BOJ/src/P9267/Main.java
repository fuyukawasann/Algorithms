package P9267;

import java.io.*;
import java.util.*;

public class Main {

	static long a, b, S;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub		
		System.setIn(new FileInputStream("src/P9267/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// a, b, S 받기
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		S = Long.parseLong(st.nextToken());
		
		// S = 0일 때
		if(S == 0) {
			if(a > 0 && b > 0) System.out.println("NO");
			else System.out.println("YES");
			return;
		}
		
		// a = 0일 때
		if(a == 0) {
			if(b == 0) System.out.println("NO");
			else {
				if( S % b == 0 ) System.out.println("YES");
				else System.out.println("NO");
			}
			return;
		}
		
		// b = 0일 때
		if(b == 0) {
			if( S % a == 0) System.out.println("YES");
			else System.out.println("NO");
			return;
		}
		
		// a = S 또는 b = S 일 때
		if( a == S || b == S) {
			System.out.println("YES");
			return;
		}
		
		// aX + bY = S
		// 확장 유클리드 호제법 사용 -> as + bt = r q
		ESGD res = eUclid(a, b);
		
		if(res.r != 1) {
			System.out.println("NO");
			return;
		}
		
		// c % d = 0이여야 해 있는 거임
		if(S % res.r != 0) {
			System.out.println("NO");
			return;
		}
		
		// 초기해
		// x0 = s*(c/d), y0 = t*(c/d)
		long x0 = res.s * (S / res.r);
		long y0 = res.t * (S / res.r);
		
		// x >= 0이고 y >= 0임
		// x = x0 + (b/d)*k >= 0
		// (- x0) * (d / b) <= k
		// y = y0 - (a/d)*k >= 0
		// - y0 <= -(a/d)*k
		// (y0) * (d / a) >= k
		
		// k의 범위는 즉
		// (- x0) * (d / b) <= k <= (y0) * (d / a)
		// 범위 확인 필요
		long lowerK = (long) Math.ceil(((-1) * x0) * (res.r / (double)b));
		long upperK = (long) Math.floor((y0) * (res.r / (double)a));
		
		// 만약 lower가 더 크면 해가 없는 거임
		if(lowerK > upperK) {
			System.out.println("NO");
			return;
		}
		
		// 만약 X와 Y의 차가 0 또는 홀수면 해가 있는거고 아니면 없는 것임
		long x = x0 + (b / res.r) * upperK;
		long y = y0 - (a / res.r) * upperK;
		
		// 확인용
		// System.out.println("x: " + x + ", y: " + y);
		
		// 만약 x = y 둘다 0이면 그건 해가 없는 거임
		if(x > 0 && y > 0 && res.r == 1) {
			System.out.println("YES");
		} else System.out.println("NO");
	}
	
	static ESGD eUclid(long a, long b) {
		long s1 = 1, t1 = 0, r1 = a;
		long s0 = 0, t0 = 1, r0 = b;
		long temp;
		
		while(r0 != 0) {
			
			long q = r1/r0;
			
			temp = r1 - q * r0;
			r1 = r0;
			r0 = temp;
			
			temp = s1 - q * s0;
			s1 = s0;
			s0 = temp;
			
			temp = t1 - q * t0;
			t1 = t0;
			t0 = temp;
		}
		
		// 결과 반환
		return new ESGD(s1, t1, r1);
	}
	
	
	static class ESGD {
		long s, t, r;
		
		public ESGD(long s, long t, long r) {
			this.s = s;
			this.t = t;
			this.r = r;
		}
	}

}
