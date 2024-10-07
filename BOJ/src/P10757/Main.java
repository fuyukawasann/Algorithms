package P10757;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P10757/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 두 수를 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb1 = new StringBuilder();
		String num1 = sb1.append(st.nextToken()).reverse().toString();
		
		StringBuilder sb2 = new StringBuilder();
		String num2 = sb2.append(st.nextToken()).reverse().toString();
		
		int carry = 0;
		StringBuilder ans = new StringBuilder();
		// 둘 중 제일 긴 숫자만큼 자릿수를 반복
		int len_num1 = num1.length();
		int len_num2 = num2.length();
		
		// num1이 더 길 때
		if(len_num1 > len_num2)
		{
			// num2 길이 만큼 일단 계산함
			for(int i = 0; i < len_num2; i++)
			{
				int sum = (num1.charAt(i) - '0') + (num2.charAt(i) - '0') + carry;
				
				carry = sum / 10;
				ans.append(sum - 10 * carry);
			}
			
			// 남은 num1을 계산함
			for(int i = len_num2; i < len_num1; i++){
				int sum = (num1.charAt(i) - '0') + carry;
				
				carry = sum / 10;
				ans.append(sum - 10 * carry);
			}
			
			// 남은 carry가 0이 아닐 때 이를 정답에 반영
			if(carry != 0)
			{
				while(carry > 0)
				{
					ans.append(carry % 10);
					carry /= 10;
				}
			}
			
		}
		// num2가 더 길 때
		else if(len_num2 > len_num1)
		{
			// num1 길이 만큼 일단 계산함
			for(int i = 0; i < len_num1; i++)
			{
				int sum = (num1.charAt(i) - '0') + (num2.charAt(i) - '0') + carry;
				
				carry = sum / 10;
				ans.append(sum - 10 * carry);
			}
			
			// 남은 num2을 계산함
			for(int i = len_num1; i < len_num2; i++){
				int sum = (num2.charAt(i) - '0') + carry;
				
				carry = sum / 10;
				ans.append(sum - 10 * carry);
			}
			
			// 남은 carry가 0이 아닐 때 이를 정답에 반영
			if(carry != 0)
			{
				while(carry > 0)
				{
					ans.append(carry % 10);
					carry /= 10;
				}
			}
		}
		// 같을 때
		else
		{
			for(int i = 0; i < len_num1; i++)
			{
				// 지금 자리 수와 캐리를 더한다.
				int sum = (num1.charAt(i) - '0') + (num2.charAt(i) - '0') + carry;
				
				// carry는 sum을 10으로 나눈 몫
				carry = sum / 10;
				// 지금 자리 수는 sum 에서 10 * carry를 뺀 수
				ans.append(sum - 10 * carry);
			}
			// 마지막에 carry가 0이 아니라면 남아있는 carry도 반영
			if(carry != 0) {
				while(carry > 0) {
					ans.append(carry % 10);
					carry /= 10;
				}
			}
		}
		
		// ans를 뒤집은 것이 정답
		System.out.println(ans.reverse().toString());

	}

}
