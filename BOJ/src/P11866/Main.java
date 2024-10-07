package P11866;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P11866/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N과 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] isAlive = new boolean[N + 1];
		Arrays.fill(isAlive, true);
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		int dead = 0;
		int idx = 0;
		while(dead != N)
		{
			int count = 0;
			while(count < K)
			{
				idx++;
				// 만약 idx가 N을 넘어가면 1로 강제로 바꿈
				if(idx > N) idx = 1;
				// 아직 살아 있을 때만 count를 올림
				if(isAlive[idx]) count++;
			}
			
			// 지금 idx를 큐에 넣음
			queue.offer(idx);
			isAlive[idx] = false;
			dead++;
		}
		
		// 큐를 출력
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!queue.isEmpty())
		{
			sb.append(queue.poll()).append(", ");
		}
		
		// 마지막에 처리하기 위해 두개를 뺀다.
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
