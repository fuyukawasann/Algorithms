package P008_좋은수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P008_좋은수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		// 찾기
		int count = 0;
		
		for(int k = 0; k < N; k++) {
			long find = nums[k];
			int i = 0;
			int j = N - 1;
			
			while(i < j) {
				long sum = nums[i] + nums[j];
				if(sum > find) {
					j--;
				}
				else if(sum < find) {
					i++;
				}
				else {
					if(i != k && j != k) {
						count++;
						break;
					} else if(i == k) {
						i++;
					} else if(j == k) {
						j--;
					}
				}
			}
		}
		
		System.out.println(count);

	}

}
