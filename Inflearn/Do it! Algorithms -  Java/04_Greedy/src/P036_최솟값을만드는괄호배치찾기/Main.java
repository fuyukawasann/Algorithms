package P036_최솟값을만드는괄호배치찾기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P036_최솟값을만드는괄호배치찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String example = br.readLine();
		String[] str = example.split("-");
		for (int i = 0; i < str.length; i++) {
			int temp = mySum(str[i]);
			
			if(i == 0) {
				answer += temp;
			}
			else answer -= temp;
		}
		
		System.out.println(answer);
		
	}
	
	static int mySum(String a) {
		int sum = 0;
		String temp[] = a.split("[+]");
		for(int i = 0; i < temp.length; i++) {
			sum += Integer.parseInt(temp[i]);
		}
		return sum;
	}

}
