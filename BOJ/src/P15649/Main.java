package P15649;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	
	static boolean[] visited;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N�� M�� �޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// �迭 ����
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			dfs(i, 1);
		}

	}
	
	static void dfs(int number, int count)
	{
		// 1. üũ��
		visited[number] = true;
		answer.add(number);
		// 2. �������ΰ� - count�� M�� ��
		if(count == M)
		{
			StringBuilder sb = new StringBuilder();
			for(int num : answer)
			{
				sb.append(num).append(" ");
			}
			System.out.println(sb.toString());
		}
		else
		{
			// 3. ��ȸ�Ѵ�. - 1���� N����
			for(int i = 1; i <= N; i++)
			{
				// 4. �� �� �ִ°� - ������ �湮 ���� ��
				if(!visited[i])
				{
					// 5. ����
					dfs(i, count + 1);
				}	
			}
		}
		
		// 6. üũ�ƿ�
		answer.remove(answer.size() - 1);
		visited[number] = false;
	}

}
