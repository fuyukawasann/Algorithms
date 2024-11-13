package P034_수를묶어서최댓값만들기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P034_수를묶어서최댓값만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> plusPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> minusPQ = new PriorityQueue<Integer>();
		int one = 0;
		int zero = 0;
		
		for(int i = 0; i < N; i++) {
			int data = Integer.parseInt(br.readLine());
			if(data > 1) {
				plusPQ.offer(data);
			}
			else if(data == 1) {
				one++;
			}
			else if(data == 0) {
				zero++;
			}
			else {
				minusPQ.offer(data);
			}
		}
		
		int sum = 0;
		
		while(plusPQ.size() > 1) {
			int first = plusPQ.poll();
			int second = plusPQ.poll();
			sum = sum + first * second;
		}
		if(!plusPQ.isEmpty()) {
			sum = sum + plusPQ.poll();
		}
		
		// 음수
		while(minusPQ.size() > 1) {
			int first = minusPQ.poll();
			int second = minusPQ.poll();
			sum = sum + first * second;
		}
		if(!minusPQ.isEmpty()) {
			if(zero == 0) {
				sum = sum + minusPQ.remove();
			}
		}
		// 1처리
		sum = sum + one;
		System.out.println(sum);

	}

}
