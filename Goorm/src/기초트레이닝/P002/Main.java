package 기초트레이닝.P002;

import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/기초트레이닝/P002/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String target = br.readLine();
		
		for(int i = 0; i < N; i++)
		{
			char now = target.charAt(i);
			if('A' <= now && now <= 'Z')
			{
				sb.append((char)(now - 'A' + 'a'));
			}
			else
			{
				sb.append((char)(now - 'a' + 'A'));
			}
		}
		
		System.out.println(sb.toString());
	}
}