package P1987;

import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, max;
	static char[][] map;
	static boolean[] visited = new boolean[26];
	static boolean[][] mapVisit;
	
	// ������ - ��, ��, ��, ��
	static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// ���� �б�
		System.setIn(new FileInputStream("src/P1987/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// R, C �ޱ�
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// ���� ����
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
		// 1. üũ��
		visited[map[row][col] - 'A'] = true;
		mapVisit[row][col] = true;
		// 2. �������ΰ�
		// 3. ��ȸ�Ѵ�
		boolean cantMove = true;
		for(int i = 0; i < 4; i++)
		{
			int nr = row + dR[i], nc = col + dC[i];
			// 4. �� �� �ִ°�
			if(0 <= nr && nr < R && 0 <= nc && nc < C)
			{
				if(!visited[map[nr][nc] - 'A'] && !mapVisit[nr][nc])
				{
					// 5. ����
					cantMove = false;
					dfs(nr, nc, cnt + 1);
				}
			}
			
		}
		
		// �� ���� ���ٸ� max�� ������Ʈ
		if(cantMove) max = Math.max(max, cnt);
		
		// 6. üũ �ƿ�
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
