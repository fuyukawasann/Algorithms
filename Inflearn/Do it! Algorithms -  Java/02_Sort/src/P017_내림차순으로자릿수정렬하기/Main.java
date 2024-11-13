package P017_내림차순으로자릿수정렬하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P017_내림차순으로자릿수정렬하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		for(char c : br.readLine().toCharArray()) {
			arr.add(Integer.valueOf(c - '0'));
		}
		
		StringBuilder sb = new StringBuilder();
		
		Collections.sort(arr, (o1, o2) -> (o2 - o1));
		
		for(int i = 0; i < arr.size(); i++) {
			sb.append(arr.get(i));
		}
		
		System.out.println(sb.toString());
	}

}
