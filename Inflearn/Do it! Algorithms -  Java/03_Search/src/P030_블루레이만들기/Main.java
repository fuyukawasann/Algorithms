package P030_블루레이만들기;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P030_블루레이만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		int start = 0;
		int end = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(start < nums[i]) start = nums[i];
			end += nums[i];
		}
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			int count = 0;
			for(int i = 0; i < N; i++) {
				if(sum + nums[i] > mid) {
					count++;
					sum = 0;
				}
				sum += nums[i];
			}
			if(sum != 0) {
				count++;
			}
			if(count > M) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		System.out.println(start);
	}

}
