package P20040;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P20040/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 M을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열을 선언
		parent = new int[N];
		
		// parent 초기화
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int result = 0;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(union(a, b)) {
				result = i;
				break;
			}
		}
		
		// 결과를 출력
		System.out.println(result);
		
		
		
	}
	
	static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			if(pA < pB) {
				parent[pB] = pA;
			}
			else parent[pA] = pB;
			return false;
		}
		else return true;
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
