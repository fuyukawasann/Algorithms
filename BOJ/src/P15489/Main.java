package P15489;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] pascal;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P15489/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// R, C, W 설정
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		// 파스칼의 삼각형 만들기
		// make 31 * 31 dp array
		pascal = new int[31][31];
		
		// 연산을 시작
		System.out.println(solve(R, C, W));
		
		
	}
	
	static int solve(int R, int C, int W) {
		int result = 0;
		
		for(int w = 1; w <= W; w++) {
			for(int j = 1; j <= w; j++) {
				result += findPascal(R + w - 1, C + j - 1);
			}
		}
		
		return result;
	}
	
	static int findPascal(int r, int c) {
		if(r == c || c == 1) {
			return pascal[r][c] = 1;
		} else if(pascal[r][c] != 0) {
			return pascal[r][c];
		} else {
			return pascal[r][c] = findPascal(r - 1, c - 1) + findPascal(r - 1, c);
		}
		
	}

}
