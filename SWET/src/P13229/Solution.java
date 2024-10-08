package P13229;

import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P13229/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 텤
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			String now = br.readLine();
			
			if(now.equals("SUN")) sb.append(7).append("\n");
			else if(now.equals("MON")) sb.append(6).append("\n");
			else if(now.equals("TUE")) sb.append(5).append("\n");
			else if(now.equals("WED")) sb.append(4).append("\n");
			else if(now.equals("THU")) sb.append(3).append("\n");
			else if(now.equals("FRI")) sb.append(2).append("\n");
			else sb.append(1).append("\n");
			
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 자원 반환
		bw.close();
		br.close();
	}

}
