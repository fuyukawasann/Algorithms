package P10866;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P10866/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int n = 1; n <= N; n++)
		{
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if(command.equals("push_front"))
			{
				int num = Integer.parseInt(st.nextToken());
				deque.addFirst(num);
			}
			else if(command.equals("push_back"))
			{

				int num = Integer.parseInt(st.nextToken());
				deque.addLast(num);
			}
			else if(command.equals("pop_front"))
			{
				if(deque.isEmpty()) sb.append(-1).append("\n");
				else sb.append(deque.pollFirst()).append("\n");
			}
			else if(command.equals("pop_back"))
			{
				if(deque.isEmpty()) sb.append(-1).append("\n");
				else sb.append(deque.pollLast()).append("\n");
			}
			else if(command.equals("size"))
			{
				sb.append(deque.size()).append("\n");
			}
			else if(command.equals("empty"))
			{
				if(deque.isEmpty()) sb.append(1).append("\n");
				else sb.append(0).append("\n");
			}
			else if(command.equals("front"))
			{
				if(deque.isEmpty()) sb.append(-1).append("\n");
				else sb.append(deque.peekFirst()).append("\n");
			}
			else
			{
				if(deque.isEmpty()) sb.append(-1).append("\n");
				else sb.append(deque.peekLast()).append("\n");
			}

		}
		
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
