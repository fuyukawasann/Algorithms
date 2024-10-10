package DAY02.P004;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int[][][] dis;
	static int W, H;
	
	// 조이콘 - 상, 하, 좌, 우
	static int[] dH = {-1, 1, 0, 0}, dW = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY02/P004/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// W, H 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 지도 생성
		map = new int[H][W];
		dis = new int[2][H][W];
		int[] start = new int[2];
		int[] knight = new int[2];
		
		// 지도를 받는다.
		
		for(int h = 0; h < H; h++)
		{
			st = new StringTokenizer(br.readLine());
			for(int w = 0; w < W; w++)
			{
				map[h][w] = Integer.parseInt(st.nextToken());
				
				// 만약 2라면 start에 넣는다.
				if(map[h][w] == 2)
				{
					start[0] = h;
					start[1] = w;
				}
				
				// 만약 3이라면 knight에 넣는다.
				if(map[h][w] == 3)
				{
					knight[0] = h;
					knight[1] = w;
				}
			}
		}
		
		// bfs로 영희 ~ 딸기 거리와 기사 ~ 딸기 거리를 측정한다.
		bfs(0, start);
		bfs(1, knight);
		
		StringBuilder sb = new StringBuilder();
		
		int result = Integer.MAX_VALUE;
		
		for(int h = 0; h < H; h++)
		{
			for(int w = 0; w < W; w++)
			{
				if(map[h][w] == 4 && dis[0][h][w] > 0 && dis[1][h][w] > 0)
				{
					result = Math.min(result, dis[0][h][w] + dis[1][h][w]);
				}
			}
		}
		
		sb.append(result - 2).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		
		
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static void bfs(int idx, int[] start)
	{		
		// 큐 선언
		ArrayDeque<Yeonghi> queue = new ArrayDeque<>();
		
		// 시작점을 넣는다.
		queue.offer(new Yeonghi(start[0], start[1]));
		
		// 시작점의 dis를 1로 설정한다.
		dis[idx][start[0]][start[1]] = 1;
		
		// 큐가 빌 때까지 한다.
		while(!queue.isEmpty())
		{
			// 1. 큐에서 꺼낸다.
			Yeonghi now = queue.poll();
			// 2. 목적지인가? - 없음			
			// 3. 순회한다.
			for(int i = 0; i < 4; i++)
			{
				int nh = now.h + dH[i], nw = now.w + dW[i];
				// 4. 갈 수 있는가?
				if(0 <= nh && nh < H && 0 <= nw && nw < W)
				{
					// dis가 0이 아니면 못 감 (그리고 지도가 1이여도 못감)
					if(dis[idx][nh][nw] == 0 && map[nh][nw] != 1)
					{
						// 5. 체크인
						dis[idx][nh][nw] = dis[idx][now.h][now.w] + 1;
						// 6. 큐에 넣는다.
						queue.offer(new Yeonghi(nh, nw));
					}
				}
				
			}			
		}
	}

}

class Yeonghi
{
	int h, w;
	
	public Yeonghi(int h, int w)
	{
		this.h = h;
		this.w = w;
	}
}
