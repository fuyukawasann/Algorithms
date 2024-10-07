package P2839;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P2839/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean isPossible = false;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i <= 5000; i++)
		{
			for(int j = 0; j <= 5000; j++)
			{
				int now = 5 * i + 3 * j;
				if(now == N) {
					isPossible = true;
					min = Math.min(min, i + j);
				}
			}
		}
		
		if(isPossible) System.out.println(min);
		else System.out.println(-1);

	}

}
