package P14621;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parent;
	static char[] namyeo;
	static int[][] edgeList;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P14621/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N, M을 받는다.
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
		
		// 도로를 받는다.
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			edgeList[i][0] = Integer.parseInt(st.nextToken());
			edgeList[i][1] = Integer.parseInt(st.nextToken());
			edgeList[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 도로를 비용 낮은 순으로 배열한다.
		Arrays.sort(edgeList, (o1, o2) -> (o1[2] - o2[2]));
		
		// 크루스칼로 해결한다.
		int result = kruskal();
		
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		// 자원반환
		bw.close();
		br.close();

	}
	
	static int kruskal()
	{
		int mstCost = 0;
		int edgeCount = 0;
		
		for(int i = 0; i < M; i++)
		{
			// 일단 둘을 꺼내서 이미 같은 집합인지 확인
			// 같은 집합이라면 다음으로 건너감
			if(find(edgeList[i][0]) == find(edgeList[i][1])) continue;
			
			// 시점과 종점의 학교가 같아도 안됨;;
			if(namyeo[edgeList[i][0]] == namyeo[edgeList[i][1]]) continue;
			// 다른 집합이라면 union
			union(edgeList[i][0], edgeList[i][1]);
			// cost를 반영
			mstCost += edgeList[i][2];
			// edgeCount를 올림
			edgeCount++;
			
			// 만약 edge가 N - 1개면 최소 스패닝 트리 완성
			if(edgeCount == N - 1)
			{
				return mstCost;
			}
		}
		
		// 만약 다 돌고 나서 N - 1개 edge면 mstCost 반환 그외는 -1
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
		else return parent[a] = find(parent[a]); // 경로 단축
	}

}
