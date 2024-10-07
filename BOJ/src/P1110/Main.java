package P1110;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P1110/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long result = 1;
		
		// 첫 번째 연산을 한다.
		// 첫째 자리
		int first = 0;
		if(N > 9) first = N / 10;
		else first = 0;
		// 둘째 자리
		int second = N - 10 * first;
		StringBuilder sb = new StringBuilder();
		sb.append(second);
		int temp = first + second;
		while(temp >= 10)
		{
			temp = temp % 10;
		}
		sb.append(temp);
		
		int next = Integer.parseInt(sb.toString());
		
		
		while(N != next)
		{
			result++;
			if(next > 9) first = next / 10;
			else first = 0;
			second = next - 10 * first;
			
			StringBuilder sb1 = new StringBuilder();
			sb1.append(second);
			temp = first + second;
			while(temp >= 10) {
				temp = temp % 10;
			}
			sb1.append(temp);
			
			next = Integer.parseInt(sb1.toString());
		}
		
		// 결과 출력
		System.out.println(result);

	}

}
