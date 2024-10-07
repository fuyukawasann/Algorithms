package P10773;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P10773/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> number = new ArrayList<>();
		
		long result = 0;
		for(int i = 0; i < N; i++)
		{
			// 이번 숫자를 받는다.
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0) {
				result -= number.get(number.size() - 1);
				number.remove(number.size() - 1);
			}
			else
			{
				result += num;
				number.add(num);
			}
		}
		
		// 결과를 출력
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
