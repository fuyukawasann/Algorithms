package 기초트레이닝.P004;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P004/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(A + B);
	}
}