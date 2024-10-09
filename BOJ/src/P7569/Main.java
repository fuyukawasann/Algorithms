package P7569;

import java.io.*;
import java.util.*;

public class Main {

	static int M, N, H; // M: 가로, N: 세로, H: 높이
	
	static int[][][] box;
	static ArrayDeque<tomato> queue;
	
	// 조이콘 - 상, 하, 좌, 우, 윗칸, 아랫칸
	static int[] dn = {-1, 1, 0, 0, 0, 0}, dm = {0, 0, -1, 1, 0, 0}, dh = {0, 0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P7569/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// M, N, H를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		box = new int[H][N][M];
		queue = new ArrayDeque<>();
		
		// 상자를 채운다.
		boolean isAlreadyRoten = true;
		
		for(int h = 0; h < H; h++)
		{
			for(int n = 0; n < N; n++)
			{
				st = new StringTokenizer(br.readLine());
				
				for(int m = 0; m < M; m++)
				{
					box[h][n][m] = Integer.parseInt(st.nextToken());
					
					// 0이라면 안 익은 게 있으므로 isAlreadyRoten을 false
					if(box[h][n][m] == 0) isAlreadyRoten = false;
					
					// 1이라면 큐에 넣는다.
					if(box[h][n][m] == 1) queue.offer(new tomato(m, n, h, 0));
				}
			}
		}
		
		
		// 아직 안 익은 토마토가 있을 때만 bfs를 실행
		StringBuilder sb = new StringBuilder();
		if(isAlreadyRoten) {
			sb.append(0).append("\n");
		}
		else
		{
			int result = bfs();
			
			// 상자들을 돌면서 아직 안 익은 것이 있는지 확인
			boolean isntRoten = false;
			for(int h = 0; h < H; h++)
			{
				for(int n = 0; n < N; n++)
				{
					for(int m = 0; m < M; m++)
					{
						if(box[h][n][m] == 0)
						{
							isntRoten = true;
							break;
						}
					}
					
					if(isntRoten) break;
				}
				
				if(isntRoten) break;
			}
			
			// 안 익은게 있으면 -1을 출력
			if(isntRoten) sb.append(-1).append("\n");
			else sb.append(result).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		// 자원 반환
		bw.close();
		br.close();	

	}
	
	static int bfs()
	{
		int day = 0;
		
		
		while(!queue.isEmpty())
		{
			// 1. 큐에서 꺼낸다.
			tomato t = queue.poll();
			day = t.day;
			
			// 2. 목적지 인가?
			// 3. 순회한다.
			for(int i = 0; i < 6; i++)
			{
				int nn = t.n + dn[i], nm = t.m + dm[i], nh = t.h + dh[i];
				// 4. 갈 수 있는가?
				if(0 <= nn && nn < N && 0 <= nm && nm < M && 0 <= nh && nh < H)
				{
					if(box[nh][nn][nm] == 0)
					{
						// 5. 체크인
						box[nh][nn][nm] = 1;
						// 6. 큐에 넣는다.
						queue.offer(new tomato(nm, nn, nh, day + 1));
					}
				}
				
			}
			
		}
		
		
		
		return day;
	}

}

class tomato
{
	int m, n, h, day;
	
	public tomato(int m, int n, int h, int day)
	{
		this.m = m;
		this.n = n;
		this.h = h;
		this.day = day;
	}
}