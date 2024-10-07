package P1924;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int month = Integer.parseInt(st.nextToken());
		int date = Integer.parseInt(st.nextToken());
		
		// 각 달 별 시작 요일을 입력한다.
		// 요일
		String[] day = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		int[] startDay = new int[] {-1, 1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6};
		
		// 지금 시작일
		int nowStartDay = startDay[month];
		date = date % 7;
		// 만약 date가 0이면 7로 바꿈
		if(date == 0) date = 7;
		
		int nowDate = (nowStartDay + date - 1) % 7;
		System.out.println(day[nowDate]);

	}

}
