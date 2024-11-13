package P1517;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] num;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1517/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		// Do bubble sort;
		System.out.println(bubbleSort());
		
	}
	
	static int bubbleSort() {
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < N; j++) {
				// 만약, 이전 숫자가 더 크다면
				if(num[j-1] > num[j]) {
					// 카운트를 올리고 숫자를 바꿈
					count++;
					int temp = num[j-1];
					num[j-1] = num[j];
					num[j] = temp;
				}
			}
		}
		
		return count;
	}
}
