package P043_최대공약수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P043_최대공약수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// A, B 받기
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		// A, B의 gcd를 출력
		long oneNum = gcd(A, B);
		
		StringBuilder sb = new StringBuilder();
		for(long i = 1; i <= oneNum; i++) {
			sb.append(1);
		}
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	
	}
	
	static long gcd(long A, long B) {
		long r = A % B;
		
		while(r != 0) {
			A = B;
			B = r;
			r = A % B;
		}
		
		return B;
	}

}
