package P12837;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, Q, S; // 날 수, 쿼리 수, 인덱스 트리 노드 수
	static long[] tree;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P12837/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N과 Q를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		S = 1;
		
		while(S < N)
		{
			S <<= 1;
		}
		
		// 트리 만듦
		tree = new long[2 * S];
		
		// 쿼리 실행
		StringBuilder sb = new StringBuilder();
		for(int query = 1; query <= Q; query++)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			// 연산이 1이면 업데이트
			if(op == 1)
			{
				int p = Integer.parseInt(st.nextToken());
				long x = Long.parseLong(st.nextToken());
				
				update(p, x);
			}
			// 아니면 쿼리
			else
			{
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, S, 1, p, q)).append("\n");
			}
		}
		
		// 정답을 입력
		bw.write(sb.toString());
		
		// 정답 출력
		bw.flush();
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	// 쿼리
	static long query(int left, int right, int node, int qL, int qR)
	{
		// 범위 밖이면 0을 반환
		if(right < qL || qR < left) return 0;
		// 만약 범위 안이다! 값을 반환
		else if(qL <= left && right <= qR) return tree[node];
		// 아니면 자식에게 위임
		else
		{
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, qL, qR) + query(mid + 1, right, node * 2 + 1, qL, qR);
		}
	}
	
	// 업데이트
	static void update(int target, long valDiff)
	{
		// 노드를 구한다.
		int node = S + target - 1;
		// 노드에 변화를 반영
		tree[node] += valDiff;
		// 부모 노드 접근
		node /= 2;
		
		while(node != 0)
		{
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
	

}
