package P096_가장길게증가하는부분수열찾기;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int[] input, LIS, trace;
	static int N;
	static int lisIdx;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P096_가장길게증가하는부분수열찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N];
		LIS = new int[N];
		trace = new int[N];
		lisIdx = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			
			if(i == 0) {
				LIS[i] = input[i];
				trace[i] = lisIdx;
			}
			else if(input[i] > LIS[lisIdx]) {
				// LIS의 마지막보다 값이 크면 그냥 뒤에 붙임
				LIS[++lisIdx] = input[i];
				trace[i] = lisIdx;
			}
			else {
				// input[i]의 값의 lowerbound를 찾아서 dp[0] ~ dp[lisIdx]에서 찾아 input[i]로 업데이트
				trace[i] = lowerBound(lisIdx, input[i]);
				LIS[trace[i]] = input[i];
			}
		}
		
		// 길이를 먼저 저장
		StringBuilder sb = new StringBuilder();
		sb.append(lisIdx + 1).append("\n");
		
		// trace를 뒤에서부터 탐색하면서 trace 값이 idx와 동일하다면 input의 값을 stack에 넣음
		int idx = lisIdx;
		ArrayList<Integer> stack = new ArrayList<>();
		
		for(int i = N-1; i >= 0; i--) {
			if(trace[i] == idx) {
				stack.add(input[i]);
				idx--;
			}
		}
		
		// 스택을 비우면서 정답 입력
		while(!stack.isEmpty()) {
			sb.append(stack.remove(stack.size() - 1)).append(" ");
		}
		sb.append("\n");
		
		// 정답을 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int lowerBound(int idx, int target) {
		int mid;
		int left = 0;
		int right = idx;
		
		while(left < right) {
			mid = (left + right) / 2;
			if(LIS[mid] >= target) {
				right = mid;
			}
			else {
				left = mid + 1;
			}
		}
		
		return right;
	}

}
