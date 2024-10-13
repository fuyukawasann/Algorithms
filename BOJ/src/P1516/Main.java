package P1516;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] time, indegree, answer;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P1516/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N];
		answer = new int[N];
		indegree = new int[N];
		adjList = new ArrayList[N];
		
		// ���� ����Ʈ �ʱ�ȭ
		for(int i = 0; i < N; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++)
		{
			// �ɸ��� �ð��� �޴´�.
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while(true)
			{
				// ���� ���� �޴� ���� -1�̸� Ż���Ѵ�.
				int from = Integer.parseInt(st.nextToken());
				if(from == -1) break;
				
				// �׷��� �ʴٸ� ���� ����Ʈ�� �߰��ϰ� indegree�� �����Ѵ�.
				indegree[i]++;
				adjList[from - 1].add(i);
			}
		}
		
		// ���ϸ� ���� ������ �Ѵ�.
		topologicalSort();
		
		// answer�� ��ȸ�ϸ� ������ �Է��Ѵ�.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++)
		{
			sb.append(answer[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}
	
	static void topologicalSort()
	{
		// ó���� ť�� �����Ѵ�.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// indegree�� ��ȸ�ϸ� 0�� �͵��� queue�� �ְ� �ּ� �ð��� answer�� �ִ´�.
		for(int i = 0; i < N; i++)
		{
			if(indegree[i] == 0)
			{
				queue.offer(i);
				answer[i] = time[i];
			}
		}
		
		// ť�� �� ������ �����Ѵ�.
		while(!queue.isEmpty())
		{
			// ť���� ������.
			int now = queue.poll();
			// ��������Ʈ�� ��ȸ�ϸ� indegree�� �����Ѵ�.
			for(int next : adjList[now])
			{
				indegree[next]--;
				// ���� �ð� + next�� ����� �ð��� ���� ���� answer�� ������Ʈ(Math.max��)
				answer[next] = Math.max(answer[next], answer[now] + time[next]);
				// indegree�� 0�� ���� ť�� �ִ´�.
				if(indegree[next] == 0) queue.offer(next);
			}
			
		}
	}
	
	

}
