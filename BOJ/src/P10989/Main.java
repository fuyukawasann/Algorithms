package P10989;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P2750/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 우선순위 큐로 해결할 것임
		int[] nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 정렬
		Arrays.sort(nums);
		
		// 출력
		for(int i = 0; i < N; i++) {
			sb.append(nums[i]).append("\n");
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
