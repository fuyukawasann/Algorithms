package P006_연속된자연수의합구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P006_연속된자연수의합구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int start = 1, end = 1;
		long sum = 1;
		long answer = 0;
		
		while(start <= N || end <= N) {
			if(sum > N) {
				sum -= (start++);
			}
			else if(sum < N) {
				sum += (++end);
			}
			else {
				sum += (++end);
				answer++;
			}
		}
		
		System.out.println(answer);

	}

}
