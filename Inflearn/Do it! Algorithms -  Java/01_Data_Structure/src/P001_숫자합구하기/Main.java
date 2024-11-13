package P001_숫자합구하기;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력 설정 -> 디버깅 용
		System.setIn(new FileInputStream("src/P001_숫자합구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 숫자 개수를 받는다
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		
		for(char c : br.readLine().toCharArray()) {
			sum += (c - '0');
		}
		
		System.out.println(sum);
		

	}

}
