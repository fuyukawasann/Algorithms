package P2441;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = N; i >= 1; i--)
		{
			// 공백을 추가
			for(int j = 1; j <= N - i; j++)
			{
				sb.append(" ");
			}
			
			// 별을 추가
			for(int j = N - i + 1; j <= N; j++)
			{
				sb.append("*");
			}
			
			// 엔터 추가
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
