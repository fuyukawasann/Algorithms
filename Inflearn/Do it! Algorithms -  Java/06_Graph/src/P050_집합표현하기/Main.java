package P050_집합표현하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P050_집합표현하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N과 M을 선언
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 부모 집합을 할당 -> 초기화
		parent = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		
		StringBuilder sb = new StringBuilder();
		// 각 연산을 실행
		int method, a, b;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 0 - 합집합 연산을 진행
			if(method == 0) {
				union(a, b);
			}
			// 1 - 찾는 연산을 진행
			else {
				if(isCommonSet(a, b)) sb.append("YES");
				else sb.append("NO");
				sb.append("\n");
			}
		}
		
		// 답을 출력
		System.out.println(sb.toString());
		
		
	}
	
	// 합집합 연산
	static void union(int a, int b)
	{
		int aRoot = find(a);
		int bRoot = find(b);
		if(!isCommonSet(a, b)) parent[aRoot] = bRoot;
	}
	
	// 부모 찾는 연산
	static int find(int a) {
		if (parent[a] == a) return a;
		// 경로 압축을 실시
		else return parent[a] = find(parent[a]);
	}

	// 공통 부모인지 확인하는 연산
	static boolean isCommonSet(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		if(aParent == bParent) return true;
		else return false;
	}
}
