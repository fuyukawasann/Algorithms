package P039_소수와팰린드롬수중에서최솟값찾기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P039_소수와팰린드롬수중에서최솟값찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 소수 구하기
		boolean[] isPrime = new boolean[10_000_000 + 1];
		Arrays.fill(isPrime, true);
		// 0과 1은 제외
		isPrime[0] = false;
		isPrime[1] = false;
		
		// 소수 연산
		// 루트로 접근
		for(int i = 2; i * i <= 10_000_000; i++) {
			if(isPrime[i]) {
				for(int j = i * 2; j <= 10_000_000; j += i) {
					isPrime[j] = false;
				}
			}
		}		
		
		// N보다 큰 범위에서 소수일 때 팰린드롬 검사를한다.
		while(true) {
			// 소수일 때
			if(isPrime[N]) {
				// 팰린드롬이맞다면 출력 후 break
				if(Felindrom(N)) {
					System.out.println(N);
					break;
				}
			}
			N++;
		}
		
	}
	
	static boolean Felindrom(int num) {
		// 스트링 빌더로 접근할 거임!!
		StringBuilder sb = new StringBuilder();
		// 이렇게 한다음 문자 배열로 선언
		sb.append(num);
		
		char[] numArr = sb.toString().toCharArray();
		
		boolean isFelindrom = true;
		for(int i = 0; i <= numArr.length / 2; i++) {
			if(numArr[i] != numArr[numArr.length - i - 1]) {
				isFelindrom = false;
				break;
			}
		}
		
		return isFelindrom;
	}

}
