package P2231;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int answer = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 1_000_000; i++)
		{
			// 분해합을 구한다.
			int temp = i;
			
			StringBuilder sb = new StringBuilder();
			sb.append(i);
			
			for(char c : sb.toString().toCharArray())
			{
				temp += (c - '0');
			}
			
			// 만약 temp가 N과 같다면 정답으로 바꾸고 탈출
			if(temp == N)
			{
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);

	}

}
