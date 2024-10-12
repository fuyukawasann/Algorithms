package P10815;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P10815/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		// A를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		for(int i = 0; i < N; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// M을 받는다.
		M = Integer.parseInt(br.readLine());
		
		// B를 받는다.
		st = new StringTokenizer(br.readLine());
		B = new int[M];
		for(int i = 0; i < M; i++)
		{
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// B를 정렬
		Arrays.sort(A);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++)
		{
			if(binarySearch(B[i])) sb.append(1).append(" ");
			else sb.append(0).append(" ");
		}
		sb.append("\n");
		
		
		bw.write(sb.toString());
		bw.flush();
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static boolean binarySearch(int target)
	{
		boolean result = false;
		
		int left = 0, right = N - 1, mid = 0;
		
		while(left <= right)
		{
			mid = (left + right) / 2;
			
			
			// 중간값이 target보다 크면 right = mid - 1
			if(A[mid] > target) right = mid - 1;
			// 중간값이 target보다 작으면 left = mid + 1
			else if(A[mid] < target) left = mid + 1;
			// 같으면 존재하는 거임
			else
			{
				result = true;
				break;
			}
		}
		
		
		
		return result;
	}

}
