package 기초트레이닝.P001;

import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P001/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(br.readLine());
		System.out.println(sb.length());
		
	}
}