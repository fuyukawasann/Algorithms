package P7576;

import java.io.*;
import java.util.*;

public class Main {
	
	static int W, H;
	

	static int[][] map;
	static ArrayDeque<Roten> queue;
	
	// 조이콘 - 상, 하, 좌, 우
	static int[] dw = {0, 0, -1, 1}, dh = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P7576/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// W, H를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 배열 생성
		map = new int[H][W];
		queue = new ArrayDeque<>();
		
		// 지도를 받는다.
		// 이미 토마토가 익었는지 확인해야 함;;
		boolean isAlreadyRoten = true;
		for(int h = 0; h < H; h++)
		{
			st = new StringTokenizer(br.readLine());
			for(int w = 0; w < W; w++)
			{
				map[h][w] = Integer.parseInt(st.nextToken());
				
				// 만약 1이면 tomato에 넣는다.
				if(map[h][w] == 1) queue.offer(new Roten(w, h, 0));
				
				// 만약 0이라면 익지 않은 토마토가 있는 것이다.
				if(map[h][w] == 0) isAlreadyRoten = false;
			}
		}
		
		// 익은 토마토 위치에서 익을 수 있는 것들을 판단한다.
		int result = bfs();
		
		// 지도를 순회하며 제일 큰 값을 찾는다
		// 근데 0인 값이 있다면 그것도 체크한다.
		boolean isntRoten = false;
		for(int h = 0; h < H; h++)
		{
			for(int w = 0; w < W; w++)
			{
				if(map[h][w] == 0) isntRoten = true;
			}
		}
		
		// 이미 익은채로 시작했다면 0을 출력한다.
		StringBuilder sb = new StringBuilder();
		if(isAlreadyRoten) sb.append(0).append("\n");
		// 탐색 후 안 익은 것이 있다면 -1을 출력한다.
		else if(isntRoten) sb.append(-1).append("\n");
		// 그 외에는 다 익은 거니까 최댓값 -1을 출력한다.(날짜기 때문)
		else sb.append(result).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		
		// 자원 반환
		bw.close();
		br.close();

	}
	
	static int bfs()
	{
		int day = 0;
		
		// 큐가 빌 때까지 실행한다.
		while(!queue.isEmpty())
		{
			// 1. 큐에서 꺼낸다.
			Roten now = queue.poll();
			day = now.day;
			// 2. 목적지인가? - 여기서는 목적지는 없다.
			// 3. 순회한다.
			for(int i = 0; i < 4; i++)
			{
				int nw = now.w + dw[i];
				int nh = now.h + dh[i];
				// 4. 갈 수 있는가? - 지도 안에 있고 방문하지 않았는지
				if(0 <= nw && nw < W && 0 <= nh && nh < H)
				{
					if(map[nh][nw] == 0)
					{
						// 5. 체크인
						map[nh][nw] = 1;
						// 6. 큐에 넣는다
						queue.offer(new Roten(nw, nh, now.day + 1));
						
					}
				}
				
			}
			
		}
		
		return day;
	}

}

class Roten
{
	int w, h, day;
	
	public Roten(int w, int h, int day)
	{
		this.w = w;
		this.h = h;
		this.day = day;
	}
}
