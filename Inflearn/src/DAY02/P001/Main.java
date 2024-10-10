package DAY02.P001;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY02/P001/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		boolean isNumInput = false;
		long result = 0;
		for(int i = 0; i < input.length(); i++)
		{
			if('0' <= input.charAt(i) && input.charAt(i) <= '9') {
				isNumInput = true;
				sb.append(input.charAt(i));
			}
			else
			{
				if(isNumInput)
				{
					result += Long.parseLong(sb.toString());
					isNumInput = false;
					sb.setLength(0);
				}
			}
		}
		
		// 마지막이 숫자로 끝나는 거 반영
		if(sb.length() != 0)
		{
			result += Long.parseLong(sb.toString());
		}
		
		System.out.println(result);

	}

}
