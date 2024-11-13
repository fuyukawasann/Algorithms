package P20019;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P20019/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String s = br.readLine();
			int N = s.length();
			int halfN = (N - 1) / 2;
			
			StringBuilder sb = new StringBuilder();
			// 회문인가?
			// 전체 문장에 대해
			if(!isCyclic(s)) {
				sb.append("#").append(t).append(" NO");
				System.out.println(sb.toString());
				continue;
			}
			
			// 왼쪽 회문 확인
			String left = s.substring(0, halfN);
			if(!isCyclic(left)) {
				sb.append("#").append(t).append(" NO");
				System.out.println(sb.toString());
				continue;
			}
			
			if(!isCyclic(s.substring(halfN + 1, s.length()))) {
				sb.append("#").append(t).append(" NO");
				System.out.println(sb.toString());
				continue;
			}
			
			// 모든 경우의 수를 통과하면 YES 출력
			sb.append("#").append(t).append(" YES");
			System.out.println(sb.toString());
			
		}
	}
	
	static boolean isCyclic(String s) {
		
		// S의 길이의 반절과 그 다음 반절이 같은 지 확인
		StringBuilder temp = new StringBuilder();
		String half1 = "", half2 = "";
		if(s.length() % 2 != 0) {
			int halfIdx = (s.length() - 1) / 2 - 1;
			half1 = s.substring(0, halfIdx + 1);
			half2 = temp.append(s.substring(halfIdx + 2, s.length())).reverse().toString();
		}
		else {
			int halfIdx = (s.length()) / 2 - 1;
			half1 = s.substring(0, halfIdx + 1);
			half2 = temp.append(s.substring(halfIdx + 1, s.length())).reverse().toString();
		}
		
		
		if(half1.equals(half2)) return true;
		return false;
		
	}

}
