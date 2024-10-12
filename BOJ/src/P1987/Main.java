package P1987;

import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, max;
	static char[][] map;
	static boolean[] visited = new boolean[26];
	static boolean[][] mapVisit;
	
	// 조이콘 - 상, 하, 좌, 우
	static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1987/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// R, C 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 지도 생성
		map = new char[R][C];
		mapVisit = new boolean[R][C];
		
		for(int r = 0; r < R; r++)
		{
			map[r] = br.readLine().toCharArray();
		}
		
		max = 0;
		dfs(0, 0, 1);
		
		System.out.println(max);

	}
	
	static void dfs(int row, int col, int cnt)
	{
		// 1. 체크인
		visited[map[row][col] - 'A'] = true;
		mapVisit[row][col] = true;
		// 2. 목적지인가
		// 3. 순회한다
		boolean cantMove = true;
		for(int i = 0; i < 4; i++)
		{
			int nr = row + dR[i], nc = col + dC[i];
			// 4. 갈 수 있는가
			if(0 <= nr && nr < R && 0 <= nc && nc < C)
			{
				if(!visited[map[nr][nc] - 'A'] && !mapVisit[nr][nc])
				{
					// 5. 간다
					cantMove = false;
					dfs(nr, nc, cnt + 1);
				}
			}
			
		}
		
		// 안 움직 였다면 max를 업데이트
		if(cantMove) max = Math.max(max, cnt);
		
		// 6. 체크 아웃
		visited[map[row][col] - 'A'] = false;
		mapVisit[row][col] = false;
	}

}

class Coordinate
{
	int row, col, count;
	
	public Coordinate(int row, int col, int count)
	{
		this.row = row;
		this.col = col;
		this.count = count;
	}
}
