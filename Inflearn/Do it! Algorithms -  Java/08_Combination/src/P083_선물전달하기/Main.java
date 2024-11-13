package P083_선물전달하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P083_선물전달하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long mod = 1_000_000_000;
		long D[] = new long[1000001];
		D[1] = 0;
		D[2] = 1;
		for(int i = 3; i <= N; i++) {
			D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod;
		}
		System.out.println(D[N]);
	}

}
