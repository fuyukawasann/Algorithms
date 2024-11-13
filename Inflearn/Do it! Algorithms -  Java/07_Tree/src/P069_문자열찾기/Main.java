package P069_문자열찾기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P069_문자열찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		tNode root = new tNode();
		
		while(n > 0) {
			String text = br.readLine();
			tNode now = root;
			for(int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				
				if(now.next[c - 'a'] == null) {
					now.next[c - 'a'] = new tNode();
				}
				now = now.next[c - 'a'];
				if(i == text.length() - 1) now.isEnd = true;
			}
			n--;
		}
		
		int count = 0;
		
		while(m > 0) {
			String text = br.readLine();
			tNode now = root;
			
			for(int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if(now.next[c - 'a'] == null) {
					break;
				}
				now = now.next[c - 'a'];
				if(i == text.length() - 1 && now.isEnd) count++;
			}
			m--;
		}
		
		System.out.println(count);
		

	}
	
	static class tNode {
		tNode[] next = new tNode[26];
		boolean isEnd;
	}

}
