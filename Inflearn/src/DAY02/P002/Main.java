package DAY02.P002;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/DAY02/P002/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input1 = br.readLine();
		
		int[] alphabet2 = new int[26];
		
		long result = 0;		
		int input2Size = 0;
		for(char c : br.readLine().toCharArray())
		{
			alphabet2[c - 'a']++;
			input2Size++;
		}
		
		for(int i = input2Size - 1; i < input1.length(); i++)
		{
			int[] alphabet1 = new int[26];
			for(int j = 0; j < input2Size; j++)
			{
				// alphabet1을 만든다.
				alphabet1[input1.charAt(i - j) - 'a']++;
			}
			
			// 26개를 확인하며 아나그램인지 확인
			boolean isAnagram = true;
			for(int j = 0; j < 26; j++)
			{
				if(alphabet1[j] != alphabet2[j]) {
					isAnagram = false;
					break;
				}
			}
			
			if(isAnagram) result++;
		}
		
		
		System.out.println(result);

	}

}
