package P4344;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P4344/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int sum = 0;
			int[] num = new int[N];
			for(int i = 0; i < N; i++)
			{
				num[i] = Integer.parseInt(st.nextToken());
				sum += num[i];
			}
			double avg = (double)sum / N;
			
			int count = 0;
			for(int i = 0; i < N; i++)
			{
				if(avg < (double)num[i]) count++;
			}
			
			double percent = (double)count / N * 100;
			
			System.out.printf("%.3f", percent);
			System.out.println("%");
		}

	}

}
