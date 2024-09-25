package P2609;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P2609/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int GCD = gcd(A, B);
		int LCM = GCD * (A / GCD) * (B / GCD);
		
		System.out.println(GCD);
		System.out.println(LCM);

	}
	
	
	static int gcd(int a, int b) {
		
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		
		
		return a;
		
	}

}
