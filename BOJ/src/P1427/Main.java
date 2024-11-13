package P1427;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P1427/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] arr = br.readLine().toCharArray();
		
		Arrays.sort(arr);
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = arr.length - 1; i >= 0; i--) {
			sb.append(arr[i]);
		}
		
		System.out.println(sb.toString());
		
	}

}
