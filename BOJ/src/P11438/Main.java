package P11438;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int LOG = 17;
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int[] depth;
	static int[][] parent;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P11438/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[LOG + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		
		// adjList를 채운다.
		for(int i = 1; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// bfs로  부모를 설정한다.
		bfs(1);
		
		// 조상을 설정한다.
		findAncestor();
		
		// M을 받는다.
		M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}
	
	static int lca(int a, int b)
	{
		// b의 깊이가 더 깊도록 설정
		if(depth[a] > depth[b])
		{
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 둘의 depth를 같도록 설정한다.
		for(int k = LOG; k >= 0; k--)
		{
			if(depth[b] - depth[a] >= (1 << k))
			{
				b = parent[k][b];
			}
		}
		
		// 만약 a == b 면 지금이 lca임
		if(a == b) return a;
		
		// 아니라면 이제 찾으면 됌
		// 처음으로 조상이 같지 않은 지점을 찾음
		for(int k = LOG; k >= 0; k--)
		{
			if(parent[k][a] != parent[k][b])
			{
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		// 이들의 부모가 lca임
		return parent[0][a];
	}
	
	static void findAncestor()
	{
		for(int k = 1; k <= LOG; k++)
		{
			for(int v = 1; v <= N; v++)
			{
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
			}
		}
	}
	
	static void bfs(int root)
	{
		// 큐 선언
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// 루트의 depth를 0으로 설정한다.
		depth[root] = 0;
		// 방문 처리
		visited[root] = true;
		// 큐에 넣는다.
		queue.offer(root);
		
		while(!queue.isEmpty())
		{
			// 1. 큐에서 꺼낸다.
			int now = queue.poll();
			// 2. 목적지인가?
			// 3. 순회한다
			for(int next : adjList[now])
			{
				// 4. 갈 수 있는가
				if(!visited[next])
				{
					// 5. 체크인
					visited[next] = true;
					depth[next] = depth[now] + 1;
					parent[0][next] = now;
					// 6. 큐에 넣는다.
					queue.offer(next);
				}
				
			}
			
		}
	}

}
