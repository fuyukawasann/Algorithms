package P18662;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P18662/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;		
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			double c = Double.parseDouble(st.nextToken());
			
			// 각각의 차를 구한다.
			double b2a = b - a;
			double c2b = c - b;
			
			// 만약 같다면 0을 출력한다.
			StringBuilder sb = new StringBuilder();
			if(b2a == c2b) sb.append("#").append(t).append(" ").append(0).append("\n");
			else {
				// 차이를 비교해서 최솟값만 업데이트
				double diff = 0;
				// b-a 로 c 업데이트
				diff = Math.abs(b + b2a - c);
				
				// c - b로 a 업데이트
				diff = Math.min(diff, Math.abs(b - c2b - a));
				
				// c - a로 b 업데이트
				double c2a = c - a;
				diff = Math.min(diff, Math.abs(a + c2a/2 - b));
				
				// diff를 답으로 제출
				sb.append("#").append(t).append(" ").append(Math.floor(diff * 10) / 10).append("\n");
				
			}
			
			
			// 답을 출력
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();

	}

}
