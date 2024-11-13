package P2470;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P2470/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		// 수를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(nums);
		
		// 투 포인터 초기화
		int left = 0;
		int right = N - 1;
		int leftVal = nums[left];
		int rightVal = nums[right];
		int minVal = Math.abs(leftVal + rightVal);
		
		while (left < right) {
			int sum = nums[left] + nums[right];
			
			if (Math.abs(sum) < minVal) {
				minVal = Math.abs(sum);
				leftVal = nums[left];
				rightVal = nums[right];
			}
			
			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		
		// 다 끝나면 차례대로 출력
		bw.write(leftVal + " " + rightVal + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}