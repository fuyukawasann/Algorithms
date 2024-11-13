package P089_연속된정수의합구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P089_연속된정수의합구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 오른쪽 방향으로 idx 포함 최대합
		int[] L = new int[N];
		L[0] = A[0];
		int result = L[0];
		for(int i = 1; i < N; i++) {
			L[i] = Math.max(A[i], L[i - 1] + A[i]);
			result = Math.max(result, L[i]);
		}
		
		// 왼쪽 방향으로 idx를 포함한 최대 연속 합 구하기
		int[] R = new int[N];
		R[N - 1] = A[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			R[i] = Math.max(A[i], R[i + 1] + A[i]);
		}
		
		// 구간 합 배열
		for(int i = 1; i < N - 1; i++) {
			int temp = L[i - 1] + R[i + 1];
			result = Math.max(result, temp);
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		

	}

}
