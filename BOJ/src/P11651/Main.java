package P11651;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P11651/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		// 좌표 받기
		Coordinate[] coor = new Coordinate[N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			coor[i] = new Coordinate(x, y);
		}
		
		// 정렬
		Arrays.sort(coor);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(coor[i].x).append(" ").append(coor[i].y).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static class Coordinate implements Comparable<Coordinate> {
		int x, y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate o) {
			// TODO Auto-generated method stub
			// y좌표를 먼저 비교한다.
			int yres = Integer.compare(this.y, o.y);
			
			// 만약 y가 같다면 x로 정렬
			if(yres == 0) return Integer.compare(this.x, o.x);
			else return yres;
		}
	}

}
