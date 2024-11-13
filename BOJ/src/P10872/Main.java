package P10872;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P10872/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] F = new int[N + 1];
		
		// 0과 1은 1
		for(int i = 0; i <= N; i++) {
			if (i == 0 || i == 1) {
				F[i] = 1;
			}
			else F[i] = F[i - 1] * i;
		}
		
		// 정답 출력
		System.out.println(F[N]);
	}

}
