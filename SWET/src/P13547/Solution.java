package P13547;

import java.io.*;

class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P13547/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			// count o and x
			int length = 0, num_o = 0, num_x = 0;
			for(char c : br.readLine().toCharArray())
			{
				if(c == 'o') num_o++;
				else num_x++;
				
				length++;
			}
			
			// 만약 length가 15가 안 되면 추가작업
			if(num_o + (15 - length) >= 8) sb.append("YES\n");
			else sb.append("NO\n");
			
			// 출력
			bw.write(sb.toString());
			bw.flush();
			
			
		}
		
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
