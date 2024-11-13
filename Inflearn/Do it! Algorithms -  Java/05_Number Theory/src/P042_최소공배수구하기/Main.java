package P042_최소공배수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P042_최소공배수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			StringBuilder sb = new StringBuilder();
			sb.append(lcd(A, B)).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		// 자원 반환
		bw.close();
		br.close();
	}
	
	static int lcd(int a, int b) {
		// gcd를 받아온다.
		int gc = gcd(a, b);
		int da = a / gc;
		int db = b / gc;
		
		// 이 셋의 곱이 최소공배수
		return gc * da * db;
		
	}
	
	
	static int gcd(int a, int b) {
		while(b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		
		return a;
	}

}
