package P15649;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	
	static boolean[] visited;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			dfs(i, 1);
		}

	}
	
	static void dfs(int number, int count)
	{
		// 1. 체크인
		visited[number] = true;
		answer.add(number);
		// 2. 목적지인가 - count가 M일 때
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
			// 3. 순회한다. - 1부터 N까지
			for(int i = 1; i <= N; i++)
			{
				// 4. 갈 수 있는가 - 기존에 방문 안한 곳
				if(!visited[i])
				{
					// 5. 간다
					dfs(i, count + 1);
				}	
			}
		}
		
		// 6. 체크아웃
		answer.remove(answer.size() - 1);
		visited[number] = false;
	}

}
