package P2750;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P2750/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 우선순위 큐로 해결할 것임
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		while(N-- > 0) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		// 출력
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
