package P033_카드정렬하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P033_카드정렬하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		for(int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(br.readLine()));
		}

		long result = 0;
		while(!pq.isEmpty()) {
			// 작은 것 첫 번째
			long first = pq.poll();
			
			// 여기서 empty확인하고 만약 빈거면 그게 정답임
			if(pq.isEmpty()) {
//				result += first;
				break;
			}
			
			long second = pq.poll();
			
			// 둘이 더함 -> 다시 넣음
			result += (first + second);
			pq.offer(first + second);
		}
		
		System.out.println(result);

	}

}
