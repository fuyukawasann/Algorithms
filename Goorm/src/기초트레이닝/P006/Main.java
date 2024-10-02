package 기초트레이닝.P006;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P006/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String K = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			// 포함되는지 검사
			if(!st.nextToken().contains(K)) count++;
		}
		
		System.out.println(count);
	}
}