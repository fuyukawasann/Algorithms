package P13458;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 입력 설정 -> 반드시 주석 처리
		System.setIn(new FileInputStream("src/P13458/input.txt"));
		// 읽기, 쓰기 라이브러리 불러오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N을 받는다.
		int N = Integer.parseInt(br.readLine());
		// 각 시험장 별 인원을 읽어옴
		StringTokenizer A = new StringTokenizer(br.readLine());
		// B, C를 미리 읽어옴
		StringTokenizer BC = new StringTokenizer(br.readLine());
		
		// B, C 설정
		int B = Integer.parseInt(BC.nextToken());
		int C = Integer.parseInt(BC.nextToken());
		
		long count = 0;
		
		// A가 더이상 없을 때까지 연산함
		while(A.hasMoreTokens()) {
			// A를 꺼내옴
			int Ai = Integer.parseInt(A.nextToken());
			// 일단 Ai - B를 진행( Ai를 갱신)
			Ai -= B;
			// count를 1 올림(총감독관)
			count++;
			// Ai가 0보다 작거나 같다 -> 총감독관으로 해결됨 -> 다음으로 건너뜀
			if(Ai <= 0) continue;
			// (double)Ai / C를 한 뒤 ceil 결과를 반영 -> 부감독관 수를 반영
			count += (long) Math.ceil((double)Ai / C);
		}
		
		// count를 출력함
		System.out.println(count);

	}

}
