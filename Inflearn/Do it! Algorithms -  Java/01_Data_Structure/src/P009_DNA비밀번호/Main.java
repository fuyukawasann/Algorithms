package P009_DNA비밀번호;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P009_DNA비밀번호/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// S, P
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		char[] input = br.readLine().toCharArray();
		
		int start = 0;
		int end = P - 1;
		
		// 기준을 받는다
		int[] threshold = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			threshold[i] = Integer.parseInt(st.nextToken());
		}
		
		// 검사
		int count = 0;
		int[] charCount = new int[4]; // A, C, G, T
		
		// 초기 세팅
		for(int i = 0; i < P; i++) {
			if(input[i] == 'A') {
				charCount[0]++;
			} else if(input[i] == 'C') {
				charCount[1]++;
			} else if(input[i] == 'G') {
				charCount[2]++;
			} else {
				charCount[3]++;
			}
		}
		
		while(end <= input.length - 1) {
			// threshold와 비교하여 검증한다.
			boolean isValid = true;
			for(int i = 0; i < 4; i++) {
				if(threshold[i] > charCount[i]) isValid = false;
			}
			
			if(isValid) count++;
			
			// 업데이트
			// start 카운트 제외
			if(input[start] == 'A') charCount[0]--;
			else if(input[start] == 'C') charCount[1]--;
			else if(input[start] == 'G') charCount[2]--;
			else charCount[3]--;
			start++;
			
			// end 카운트 포함
			end++;
			// end가 lenght - 1 까지만 실행
			if(end <= input.length - 1) {
				if(input[end] == 'A') charCount[0]++;
				else if(input[end] == 'C') charCount[1]++;
				else if(input[end] == 'G') charCount[2]++;
				else charCount[3]++;
			}
		}
		
		// count 출력
		System.out.println(count);

	}

}
