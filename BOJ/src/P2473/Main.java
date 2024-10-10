package P2473;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P2473/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N
		int N = Integer.parseInt(br.readLine());
		
		long[] A = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(A);
		
		long val = Long.MAX_VALUE;
		long[] ans = new long[3];
		
		for(int i = 0; i < N - 2; i++)
		{
			int left = i + 1;
			int right = N - 1;
			
			while(left < right)
			{
				// 일단 합을 구함
				long sum = A[i] + A[left] + A[right];
				
				// 기존 val의 abs보다 작으면 업데이트
				if(Math.abs(sum) < Math.abs(val))
				{
					val = sum;
					ans[0] = A[i];
					ans[1] = A[left];
					ans[2] = A[right];
				}
				
				// 만약 sum이 양수라면 right를 1 감소
				if(sum > 0) right--;
				// 만약 sum이 음수라면 left를 1증가
				else left++;
			}
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		sb.append(ans[0]).append(" ").append(ans[1]).append(" ").append(ans[2]).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
