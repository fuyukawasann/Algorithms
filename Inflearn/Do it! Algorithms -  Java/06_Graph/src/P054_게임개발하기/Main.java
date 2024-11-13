package P054_게임개발하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Integer>[] adjList;
	static int[] indegree;
	static int[] buildTime;
	static int[] preBuildTime;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P054_게임개발하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		 adjList = new ArrayList[N+1];
        for(int n=1; n<=N; n++){
            adjList[n] = new ArrayList<>();
        }
		indegree = new int[N + 1];
		buildTime = new int[N + 1];
		preBuildTime = new int[N + 1];

		// 건물 별 정보 저장
		int from;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			buildTime[i] = Integer.parseInt(st.nextToken());
			
			while(true) {
				from = Integer.parseInt(st.nextToken());
				
				if(from == -1) break;
				
				adjList[from].add(i);
				indegree[i]++;
			}
		}
		
		buildStart();
		
		for(int i = 1; i <= N; i++) {
			System.out.println(buildTime[i]);
		}
		
	}
	
	public static void buildStart()
	{
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty())
		{
			int node = queue.poll();
			
			for(Integer to: adjList[node]) {
				indegree[to]--;
				
				preBuildTime[to] = Math.max(preBuildTime[to], buildTime[node]);
				
				if(indegree[to] == 0) {
					buildTime[to] += preBuildTime[to];
					queue.offer(to);
				}
			}
		}
	}
	
	

}
