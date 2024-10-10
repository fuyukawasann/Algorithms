package DAY02.P003;

import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/DAY02/P003/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] Task = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			Task[i] = Integer.parseInt(br.readLine());
		}
		
		int[] sT = Arrays.copyOf(Task, N + 1);
		Arrays.sort(sT);
		
		long K = Long.parseLong(br.readLine());
		
		int rest = N;
		
		for(int i = 1; i <= N; i++)
		{
			if(K < (rest * (sT[i] - sT[i - 1])))
			{
				int index = (int) (K % rest);
				int cnt = 0;
				
				for(int j = 1; j <= N; j++)
				{
					if(Task[j] >= sT[i])
					{
						if(cnt == index)
						{
							System.out.println(j);
							return;
						}
						cnt++;
					}
				}
			}
			else
			{
				K -= (rest * (sT[i] - sT[i - 1]));
				rest--;
			}
		}
		
		System.out.println(-1);

	}

}