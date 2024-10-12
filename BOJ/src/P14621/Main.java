package P14621;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parent;
	static char[] namyeo;
	static int[][] edgeList;

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P14621/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M�� �޴´�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		namyeo = new char[N + 1];
		edgeList = new int[M][3];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
		{
			parent[i] = i;
			namyeo[i] = st.nextToken().charAt(0);
		}
		
		// ���θ� �޴´�.
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			edgeList[i][0] = Integer.parseInt(st.nextToken());
			edgeList[i][1] = Integer.parseInt(st.nextToken());
			edgeList[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// ���θ� ��� ���� ������ �迭�Ѵ�.
		Arrays.sort(edgeList, (o1, o2) -> (o1[2] - o2[2]));
		
		// ũ�罺Į�� �ذ��Ѵ�.
		int result = kruskal();
		
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		// �ڿ���ȯ
		bw.close();
		br.close();

	}
	
	static int kruskal()
	{
		int mstCost = 0;
		int edgeCount = 0;
		
		for(int i = 0; i < M; i++)
		{
			// �ϴ� ���� ������ �̹� ���� �������� Ȯ��
			// ���� �����̶�� �������� �ǳʰ�
			if(find(edgeList[i][0]) == find(edgeList[i][1])) continue;
			
			// ������ ������ �б��� ���Ƶ� �ȵ�;;
			if(namyeo[edgeList[i][0]] == namyeo[edgeList[i][1]]) continue;
			// �ٸ� �����̶�� union
			union(edgeList[i][0], edgeList[i][1]);
			// cost�� �ݿ�
			mstCost += edgeList[i][2];
			// edgeCount�� �ø�
			edgeCount++;
			
			// ���� edge�� N - 1���� �ּ� ���д� Ʈ�� �ϼ�
			if(edgeCount == N - 1)
			{
				return mstCost;
			}
		}
		
		// ���� �� ���� ���� N - 1�� edge�� mstCost ��ȯ �׿ܴ� -1
		if(edgeCount == N - 1) return mstCost;
		else return -1;
	}
	
	static void union(int a, int b)
	{
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) parent[pB] = pA;
	}
	
	static int find(int a)
	{
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]); // ��� ����
	}

}
