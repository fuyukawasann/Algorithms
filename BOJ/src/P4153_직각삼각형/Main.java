package P4153_직각삼각형;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int[] A = new int[3];
			
			int zeroCount = 0;
			for(int i = 0; i < 3; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				if(A[i] == 0) zeroCount++;
			}
			
			// 만약 0, 0, 0이면 종료
			if(zeroCount == 3) break;
			
			Arrays.sort(A);
			
			int max = A[2] * A[2];
			int sum = A[1] * A[1] + A[0] * A[0];
			
			if(max == sum) System.out.println("right");
			else System.out.println("wrong");
		}
		

	}

}
