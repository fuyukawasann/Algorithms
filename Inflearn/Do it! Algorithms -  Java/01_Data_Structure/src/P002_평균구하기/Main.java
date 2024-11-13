package P002_평균구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 입력 받기
		System.setIn(new FileInputStream("src/P002_평균구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int M = 0; // Max Value를 저장한다.
		double fSum = 0; // 수정된 값의 합을 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			M = Math.max(M, nums[i]);
		}
		
		// 수정된 값 저장
		for(int i = 0; i < N; i++) {
			fSum += (nums[i] / (double)M) * 100;
		}
		
		System.out.println(fSum / N);
		
		

	}

}
