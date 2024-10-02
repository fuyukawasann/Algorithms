package 기초트레이닝.P003;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P003/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double A = Double.parseDouble(st.nextToken());
		double B = Double.parseDouble(st.nextToken());
		
		System.out.printf("%.6f\n", A + B);
	}
}