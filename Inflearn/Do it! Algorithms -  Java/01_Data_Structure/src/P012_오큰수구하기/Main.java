package P012_오큰수구하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P012_오큰수구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] answer = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> stack = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(stack.isEmpty()) {
				stack.add(i);
				continue;
			}
			
			// 다음에 오는 idx의 값이 현재 stack의 값보다 크다면 pop한 뒤에 해당 idx에 현재 값을 넣는다.
			while(!stack.isEmpty() && nums[stack.get(stack.size() - 1)] < nums[i]) {
				int popIdx = stack.remove(stack.size() - 1);
				answer[popIdx] = nums[i];
			}
			
			stack.add(i);
			
		}
		
		// 스택이 빌 때까지 안에 있는 idx는 모두 -1로 설정
		while(!stack.isEmpty()) {
			answer[stack.remove(stack.size() - 1)] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.append("\n");
		
		System.out.println(sb.toString());

	}

}
