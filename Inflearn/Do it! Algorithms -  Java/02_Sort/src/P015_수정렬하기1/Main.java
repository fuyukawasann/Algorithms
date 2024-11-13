package P015_수정렬하기1;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P015_수정렬하기1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N을 받는다.
		int n = Integer.parseInt(br.readLine());
		
		// 숫자 배열
		int[] nums = new int[n + 1];
		
		// 배열에 값 넣기
		for(int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 버블 정렬
		for(int i = 1; i <= n; i++) {
			for(int j = 2; j <= n - i + 1; j++) {
				if(nums[j] < nums[j - 1]) {
					int temp = nums[j];
					nums[j] = nums[j - 1];
					nums[j - 1] = temp;
				}
			}
		}
		
		
		// 정답을 출력
		for(int i = 1; i <= n; i++) {
			System.out.println(nums[i]);
		}

	}

}
