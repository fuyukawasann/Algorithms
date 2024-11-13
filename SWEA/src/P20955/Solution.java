package P20955;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P20955/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			
			// S와 E 받음...
			String s = br.readLine();
			String e = br.readLine();
			StringBuilder es = new StringBuilder();
			es.append(e);
			
			// 길이의 차만 큼 실행
			for(int i = 0; i < e.length() - s.length(); i++) {
				// 만약 마지막이 X다 -> 그냥 제거
				if(es.charAt(es.length() - 1) == 'X') {
					es.deleteCharAt(es.length() - 1);
				}
				// 만약 마지막이 Y다 -> Y를 제거 후 reverse 시
				else {
					es.deleteCharAt(es.length() - 1);
					es.reverse();
				}
			}
			
			// 만약 s와 같다면 Yes를 아니라면 No를 출력
			if(s.equals(es.toString())) {
				sb.append("#").append(t).append(" Yes");
			}
			else sb.append("#").append(t).append(" No");
			
			// 출력
			System.out.println(sb.toString());
		}
	}

}
