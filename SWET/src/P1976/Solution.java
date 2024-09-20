package P1976;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P1976/input.txt"));
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테스트 케이스 처리
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// 시간을 받는다.
			st = new StringTokenizer(br.readLine());
			// 시간 1
			int hour1 = Integer.parseInt(st.nextToken());
			int min1 = Integer.parseInt(st.nextToken());
			
			// 시간 2
			int hour2 = Integer.parseInt(st.nextToken());
			int min2 = Integer.parseInt(st.nextToken());
			
			// 분과 시를 먼저 계산
			int min = min1 + min2;
			int hour = hour1 + hour2;
			// 분이 60을 넘어가면 다시 리셋
			if(min >= 60) {
				hour += min / 60;
				min = min - (min / 60) * 60;
			}
			// 시간이 12를 넘어가면 다시 리셋
			if(hour > 12) {
				hour = hour - (hour / 12) * 12;
				// 만약 hour가 0이 되면 12로 임의 설정
				if(hour == 0) hour = 12;
			}
			
			// 정답을 입력
			sb.append(hour).append(" ").append(min).append("\n");
			
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 정답 입력
		bw.close();
		br.close();

	}

}
