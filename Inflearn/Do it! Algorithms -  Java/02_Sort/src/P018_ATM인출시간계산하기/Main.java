package P018_ATM인출시간계산하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P018_ATM인출시간계산하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] nums = new int[n];
		
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 삽입 정렬 연습
		for(int i = 1; i < n; i++) {
			int insert_point = i;
			int insert_value = nums[i];
			
			for(int j = i - 1; j >= 0; j--) {
				if(nums[j] < nums[i]) {
					insert_point = j +1;
					break;
				}
				if(j == 0) {
					insert_point = 0;
				}
			}
			for(int j = i; j > insert_point; j--) {
				nums[j] = nums[j - 1];
			}
			nums[insert_point] = insert_value;
		}
		
		
		// 합을 구한다.
		int[] pSum = new int[n];
		
		pSum[0] = nums[0];
		for(int i = 1; i < n; i++) {
			pSum[i] = pSum[i - 1] + nums[i];
		}
		
		// 합 배열 총합
		int sum = 0;
		for(int i = 0; i < n; i++) {
			sum = sum + pSum[i];
		}
		
		// 답 출력
		System.out.println(sum);

	}

}
