package P10815;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P10815/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N�� �޴´�.
		N = Integer.parseInt(br.readLine());
		
		// A�� �޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		for(int i = 0; i < N; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// M�� �޴´�.
		M = Integer.parseInt(br.readLine());
		
		// B�� �޴´�.
		st = new StringTokenizer(br.readLine());
		B = new int[M];
		for(int i = 0; i < M; i++)
		{
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// B�� ����
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
		
		// �ڿ� ��ȯ
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
			
			
			// �߰����� target���� ũ�� right = mid - 1
			if(A[mid] > target) right = mid - 1;
			// �߰����� target���� ������ left = mid + 1
			else if(A[mid] < target) left = mid + 1;
			// ������ �����ϴ� ����
			else
			{
				result = true;
				break;
			}
		}
		
		
		
		return result;
	}

}
