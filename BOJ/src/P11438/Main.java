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
		// ���� �б�
		System.setIn(new FileInputStream("src/P11438/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N�� �޴´�.
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[LOG + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		
		// adjList�� ä���.
		for(int i = 1; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		// bfs��  �θ� �����Ѵ�.
		bfs(1);
		
		// ������ �����Ѵ�.
		findAncestor();
		
		// M�� �޴´�.
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
		// b�� ���̰� �� ���� ����
		if(depth[a] > depth[b])
		{
			int temp = a;
			a = b;
			b = temp;
		}
		
		// ���� depth�� ������ �����Ѵ�.
		for(int k = LOG; k >= 0; k--)
		{
			if(depth[b] - depth[a] >= (1 << k))
			{
				b = parent[k][b];
			}
		}
		
		// ���� a == b �� ������ lca��
		if(a == b) return a;
		
		// �ƴ϶�� ���� ã���� ��
		// ó������ ������ ���� ���� ������ ã��
		for(int k = LOG; k >= 0; k--)
		{
			if(parent[k][a] != parent[k][b])
			{
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		// �̵��� �θ� lca��
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
		// ť ����
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		// ��Ʈ�� depth�� 0���� �����Ѵ�.
		depth[root] = 0;
		// �湮 ó��
		visited[root] = true;
		// ť�� �ִ´�.
		queue.offer(root);
		
		while(!queue.isEmpty())
		{
			// 1. ť���� ������.
			int now = queue.poll();
			// 2. �������ΰ�?
			// 3. ��ȸ�Ѵ�
			for(int next : adjList[now])
			{
				// 4. �� �� �ִ°�
				if(!visited[next])
				{
					// 5. üũ��
					visited[next] = true;
					depth[next] = depth[now] + 1;
					parent[0][next] = now;
					// 6. ť�� �ִ´�.
					queue.offer(next);
				}
				
			}
			
		}
	}

}
