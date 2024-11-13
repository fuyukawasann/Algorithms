package P097_선분방향구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P097_선분방향구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 좌표 받기
		StringTokenizer st;
		int[][] coor = new int[3][2];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			// x좌표
			coor[i][0] = Integer.parseInt(st.nextToken());
			// y좌표
			coor[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// CCW 계산
		int CCW = 0;
		// CCW = (x1y2 + x2y3 + x3y1) - (x2y1 + x3y2 + x1y3)
		CCW = (coor[0][0] * coor[1][1] + coor[1][0] * coor[2][1] + coor[2][0] * coor[0][1]) -
				(coor[1][0] * coor[0][1] + coor[2][0] * coor[1][1] + coor[0][0] * coor[2][1]);
		
		// 만약 CCW가 음수라면  -> 시계
		if(CCW < 0) System.out.println(-1);
		// 양수라면 반시계
		else if(CCW > 0) System.out.println(1);
		// 0이라면 일직선
		else System.out.println(0);

	}

}
