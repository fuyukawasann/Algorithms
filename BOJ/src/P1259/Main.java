package P1259;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P1259/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			int nowNumber = Integer.parseInt(br.readLine());
			
			// 숫자가 0이면 탈출
			if(nowNumber == 0) break;
			
			// 아니면 팰린드롬인지 확인
			boolean res = isFelindrom(nowNumber);
			
			if(res) System.out.println("yes");
			else System.out.println("no");
			
		}

	}
	
	static boolean isFelindrom(int num)
	{
		boolean result = true;
		
		String thisNum = String.valueOf(num);
		
		// 길이가 짝수 일 때
		if(thisNum.length() % 2 == 0)
		{
			for(int i = 0; i < thisNum.length() / 2; i++)
			{
				if(thisNum.charAt(i) != thisNum.charAt(thisNum.length() - i - 1))
				{
					result = false;
					break;
				}
			}
		}
		// 길이가 홀수일 때
		else
		{
			for(int i = 0; i < thisNum.length() / 2; i++)
			{
				if(thisNum.charAt(i) != thisNum.charAt(thisNum.length() - i - 1))
				{
					result = false;
					break;
				}
			}
		}
		
		return result;
	}

}
