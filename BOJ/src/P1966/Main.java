package P1966;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1966/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
		{
			StringBuilder sb = new StringBuilder();
			ArrayDeque<Task> task = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); // taskNo.
			
			// 중요도와 순서를 입력한다.
			int[] priorities = new int[10];
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++)
			{
				int pri = Integer.parseInt(st.nextToken());
				task.add(new Task(pri, n));
				priorities[pri]++;
			}
			
			int idx = 0;
			while(true)
			{
				Task now = task.pollFirst();
				// 지금의 priority보다 큰 게 있다면
				boolean isGreaterPExist = false;
				for(int p = now.priority + 1; p < 10; p++)
				{
					if(priorities[p] != 0) {
						isGreaterPExist = true;
						break;
					}
				}
				// 지금 것을 뒤로 붙인다.
				if(isGreaterPExist) {
					task.add(now);
				}
				
				// 그렇지 않다면
				else
				{
					idx++;
					// 현재 priority 카운트를 1 감소한다.
					priorities[now.priority]--;
					// 지금의 taskNo가 M과 같은지 비교하고 같으면
					if(now.taskNo == M)
					{
						// 정답 입력 후 탈출
						sb.append(idx).append("\n");
						break;
					}
				}
			}
			
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		bw.close();
		br.close();

	}

}

class Task
{
	int priority, taskNo;
	
	public Task(int priority, int taskNo)
	{
		this.priority = priority;
		this.taskNo = taskNo;
	}
}
