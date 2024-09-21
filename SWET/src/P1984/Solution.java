package P1984;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P1984/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테케 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			int[] nums = new int[10];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 10; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정렬
			Arrays.sort(nums);
			
			// 큰수와 작은 수를 제외한 합을 구함
			int sum = 0;
			for(int i = 1; i < 9; i++) {
				sum += nums[i];
			}
			
			// 8로 나눈뒤 반올림 -> 정답 입력
			sb.append((int) Math.round((float)sum / 8)).append("\n");
			
			// 정답 출력
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
