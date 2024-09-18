package P12909;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean answer = true;
		
		String s = br.readLine();
		int count = 0;
		boolean isNegativeStart = false;
		boolean isPositiveEnd = false;
		boolean isWrongInput = false;
		
		// ) 시작 검사
		if(s.charAt(0) == ')') isNegativeStart = true;
		// ( 끝 검사
		if(s.charAt(s.length() - 1) == '(') isPositiveEnd = true;
		
		// 괄호 검사
		for(int i = 0; i < s.length(); i++) {			
			if(s.charAt(i) == '(') count++;
			if(s.charAt(i) == ')') {
				// 만약 count가 0인데 ) 들어오면 잘못된 입력임
				if(count == 0) isWrongInput = true;
				count--;
			}
		}
		
		if(count != 0 || isNegativeStart || isPositiveEnd || isWrongInput) answer = false;
		
		System.out.println(answer);
		
		
	}

}
