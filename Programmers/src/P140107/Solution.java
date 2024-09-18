package P140107;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P140107/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		// 여기서부터 프로그래머스 부분
		long answer = 0;
		
		int max = d;
		long dist2 = (long)d * d;
		
		for(int i = 0; i <= d; i += k) {
			// max^2 + i^2이 dist2보다 작거나 같아야 함
			// 위로 올라갈 수록 max는 감소하는 방향으로 바뀔거임!!
			long i2 = (long)i * i;
			long max2 = (long)max * max;
			// 거리가 안으로 들어올 때까지 실행
			while(dist2 < max2 + i2) {
				if(max == 0) break;
				max--;
				max2 = (long)max * max;
			}
			// 거리가 안으로 들어오면 answer에 반영
			answer += (max/k + 1);
		}
		
		
		
		// 정답 출력
		System.out.println(answer);
	}

}
