package DAY01.P004;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, whiteTotalAbility, blackTotalAbility;
	static int[][] ability;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY01/P004/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 선수 수를 받는다.
		N = Integer.parseInt(st.nextToken());
		
		// 선수들의 능력을 받는다. 0: 흰돌, 1: 검은돌
		ability = new int[N][2];
		visited = new boolean[N];
		whiteTotalAbility = 0;
		blackTotalAbility = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			ability[i][0] = Integer.parseInt(st.nextToken());
			whiteTotalAbility += ability[i][0];
			
			ability[i][1] = Integer.parseInt(st.nextToken());
			blackTotalAbility += ability[i][1];
		}
		
		// DFS로 찾는다.
		int result = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			result = Math.min(DFS(i, 0 , 0), result);
		}
		
		// 결과를 출력한다.
		System.out.println(result);
		

	}
	
	static int DFS(int number, int count, int whiteAbility) {
		// 1. 체크인
		visited[number] = true;
		count++;
		whiteAbility += ability[number][0];
		blackTotalAbility -= ability[number][1];
		int tmpRes = Integer.MAX_VALUE;
		
		// 2. 목적지 인가? (count가 N / 2인가)
		if(count == N / 2) {
			// tmpRes를 whiteAbility - blackTotalAbility로 설정한다.
			tmpRes = whiteAbility - blackTotalAbility;
			// 만약 tmpRes가 음수면 양수로 바꾼다.
			if(tmpRes < 0) tmpRes = tmpRes * (-1);
		}
		else {
			// 3. 순회한다.
			for(int i = number + 1; i < N; i++) {
				// 4. 갈 수 있는가? - 방문을 안 했는가?
				if(!visited[i]) {
					// 5. 간다.
					tmpRes = Math.min(tmpRes, DFS(i, count, whiteAbility));
				}
			}
		}
		
		// 6. 체크아웃
		visited[number] = false;
		count--;
		whiteAbility -= ability[number][0];
		blackTotalAbility += ability[number][1];
		
		return tmpRes;
	}

}
