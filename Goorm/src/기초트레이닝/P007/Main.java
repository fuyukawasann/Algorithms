package 기초트레이닝.P007;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P007/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sum += Integer.parseInt(st.nextToken());
		}
		
		// 8진수 변환
		StringBuilder sb = new StringBuilder();
		while(sum >= 8)
		{
			sb.append(sum % 8);
			sum /= 8;
		}
		sb.append(sum);
		
		// 뒤집는다.
		sb.reverse();
		System.out.println(sb.toString());
	}
}