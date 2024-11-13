package P011_스택으로오름차순수열만들기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P010_스택으로오름차순수열만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] target = new int[N];
		for(int i = 0; i < N; i++) {
			target[i] = Integer.parseInt(br.readLine());
		}
		
		int idx = 0;
		int num = 1;
		ArrayList<Integer> stack = new ArrayList<>();
		
		// 첫 값으로 0을 넣는다
		stack.add(0);
		StringBuilder sb = new StringBuilder();
		while(idx < N) {
			// 만약 첫 값이 0이면 제거하고 시작
			if(!stack.isEmpty() && stack.get(0) == 0) {
				stack.remove(0);
			}
			
			// 만약 현재 stack top이 target[idx]와 같다면 제거
			if(!stack.isEmpty() && stack.get(stack.size() - 1) == target[idx]) {
				stack.remove(stack.size() - 1);
				sb.append("-\n");
				idx++;
				continue;
			}
			
			// num이 아직 N을 넘기지 않았다면
			if(num <= N) {
				// 현재 num을 넣는다.
				stack.add(num);
				num++;
				sb.append("+\n");
			}
			
			// 만약 num이 이미 N을 넘겼지만, idx와 값이 달라 -> 못 만듦
			if(num > N && stack.get(stack.size() - 1) != target[idx]) {
				sb.delete(0, sb.length());
				sb.append("NO\n");
				break;
			}
			
		}
		
		// 정답을 출력
		System.out.println(sb.toString());
	}

}
