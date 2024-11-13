package P1206;

import java.io.*;
import java.util.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P1206/sample_input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 테케
		for(int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// N을 받는다.
			int N = Integer.parseInt(br.readLine());
			
			// 건물 높이를 받는다.
			st = new StringTokenizer(br.readLine());
			
			int[] height = new int[N];
			
			for(int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			// 양 옆 두 칸으로 조망 권을 확인
			long result = 0;
			for(int i = 2; i < N - 2; i++) {
				// 좌 두칸 우 두칸 중 제일 높은 건물 높이와 현재 빌딩의 높이 비교
				int maxL = Math.max(height[i - 1], height[i - 2]);
				int maxR = Math.max(height[i + 1], height[i + 2]);
				int max = Math.max(maxL, maxR);
				
				// 현재 높이 - max가 정답인데
				// 만약 음수 면 조망권 확보 세대는 없는 거임
				if(height[i] - max > 0) result += (height[i] - max);
			}
			
			// 결과를 출력
			sb.append(result).append("\n");
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
