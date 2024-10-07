package P10817;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P10817/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] A = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		System.out.println(A[1]);

	}

}
