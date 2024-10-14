package P30802;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 파일 읽기
		System.setIn(new FileInputStream("src/P30802/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] shirt = new int[6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 6; i++)
		{
			shirt[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		int ans = 0;
		for(int i = 0; i < 6; i++)
		{
			ans += (int) Math.ceil((double)shirt[i]/T);
		}
		sb.append(ans).append("\n");
		
		sb.append(N / P).append(" ").append(N % P).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
