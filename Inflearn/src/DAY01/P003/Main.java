package DAY01.P003;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/DAY01/P003/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 학생 수를 받는다.
		int N = Integer.parseInt(br.readLine());
		int[] H = new int[N + 1];
		int[] res = new int[N + 1];
		Stack<Integer> stack = new Stack<>();
		
		// 학생 앉은 키를 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}
		
		// 뒤에서부터 보면서 방해학생인지 확인한다.
		for(int i = N; i >= 1; i--) {
			while(!stack.isEmpty() && H[i] > H[stack.peek()]) {
				res[stack.peek()] = i;
				stack.pop();
			}
			
			stack.push(i);
		}
		
		// 정답 입력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(res[i]).append(" ");
		}
		sb.append("\n");
		
		// 정답 출력
		bw.write(sb.toString());
		bw.flush();
		
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
