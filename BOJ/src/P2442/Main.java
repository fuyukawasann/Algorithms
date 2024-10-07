package P2442;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P2442/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++)
		{
			// 공백 찍기
			for(int j = N - i; j >= 1; j--)
			{
				sb.append(" ");
			}
			
			// 별찍기
			for(int j = 1; j <= 2 * i - 1; j++)
			{
				sb.append("*");
			}
			
			// 엔터
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
