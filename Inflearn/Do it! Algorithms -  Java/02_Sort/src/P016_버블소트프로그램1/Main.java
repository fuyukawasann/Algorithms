package P016_버블소트프로그램1;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P016_버블소트프로그램1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Node[] A = new Node[n];
		
		for(int i = 0; i < n; i++) {
			A[i] = new Node(Integer.parseInt(br.readLine()), i);
		}
		
		// 정렬
		Arrays.sort(A);
		
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < n; i++) {
			max = Math.max(max, A[i].start - i);
		}
		
		System.out.println(max + 1);
	}
	
	static class Node implements Comparable<Node> {
		int value, start;
		
		public Node (int value, int start) {
			this.value = value;
			this.start = start;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.value, o.value);
		}
	}

}
