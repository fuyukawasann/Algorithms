package P007_주몽의명령;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P007_주몽의명령/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N
		int N = Integer.parseInt(br.readLine());
		
		// M
		int M = Integer.parseInt(br.readLine());
		
		int[] eigenNo = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			eigenNo[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 1, end = N;
		
		long sum = 0;
		long count = 0;
		
		Arrays.sort(eigenNo);
		
		while(start < end) {
			sum = eigenNo[start] + eigenNo[end];
			if(sum > M) {
				end--;
			}
			else if(sum < M) {
				start++;
			}
			else {
				count++;
				end--;
				start++;
			}
		}
		
		System.out.println(count);

	}

}
