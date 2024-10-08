package DAY01.P002;

import java.io.*;
import java.util.*;

public class Main {
	
	static int W, H, T, S;
	
	static ArrayList<Coordinate> coorList;
	static ArrayList<Integer> X, Y;
	

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY01/P002/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// W, H, T, S를 받음
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		// 좌표 저장 및 X, Y 저장
		HashSet<Integer> xHash = new HashSet<>();
		HashSet<Integer> yHash = new HashSet<>();
		coorList = new ArrayList<>();
		X = new ArrayList<>();
		Y = new ArrayList<>();
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 좌표에 넣는다.
			coorList.add(new Coordinate(x, y));
			// x가 중복되는지 확인함 -> 중복 안되면 X에 넣는다.
			if(!xHash.contains(x)) {
				X.add(x);
				xHash.add(x);
			}
			// y가 중복되는지 확인하고 안되면 Y에 넣는다.
			if(!yHash.contains(y)) {
				Y.add(y);
				yHash.add(y);
			}
		}
		
		// X와 Y를 정렬한다.
		X.sort((o1, o2) -> (o1 - o2));
		Y.sort((o1, o2) -> (o1 - o2));
		
		// 각 좌표를 돌며 좌표 안에 들어오는지 확인한다.
		int result = 0;
		for(int i : X) {
			for(int j : Y) {
				// 개수를 세는 count
				int count = 0;
				
				// 1. coorList를 순회한다.
				for(Coordinate now : coorList) {
					// 2. 해당 x, y좌표가 토지 안에 들어오는지 확인한다.
					if(i <= now.x && now.x <= i + S && j <= now.y && now.y <= j + S) {
						// 3. 토지 안에 들어오면 count를 1 증가한다.
						count++;
					}
				}
				
				// 4. result보다 count가 크면 갱신
				if(count > result) result = count;
			}
			
		}
		
		// 결과를 입력
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		bw.write(sb.toString());
		
		
		// 결과를 출력
		bw.flush();
		
		// 자원 반환
		bw.close();
		br.close();
	}

}

class Coordinate {
	int x, y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}