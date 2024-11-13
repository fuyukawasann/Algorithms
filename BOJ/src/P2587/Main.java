package P2587;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P2587/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int[] nums = new int[5];
		
		for(int i = 0; i <= 4; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			sum += nums[i];
		}
		
		// 정렬
		Arrays.sort(nums);
		
		// 평균
		System.out.println(sum / 5);
		// 중앙값
		System.out.println(nums[2]);
	}

}
