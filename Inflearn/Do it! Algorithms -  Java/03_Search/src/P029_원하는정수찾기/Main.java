package P029_원하는정수찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] A;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P029_원하는정수찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(A);
		
		// 탐색
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++) {
			if(binarySearch(Integer.parseInt(st.nextToken()))) {
				sb.append(1).append("\n");
			}
			else sb.append(0).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	
	static boolean binarySearch(int target) {
		int left = 0;
		int right = N - 1;
		int mid = 0;
		
		boolean doesExist = false;
		while(left <= right) {
			mid = (left + right) / 2;
			// mid < target -> 왼쪽을 mid + 1로 설정
			if(A[mid] < target) left = mid + 1;
			// mid > target -> 오른쪽 mid - 1로 설정
			else if(A[mid] > target) right = mid - 1;
			// 만약 같다면 값이 존재
			else {
				doesExist = true;
				break;
			}
		}
		
		return doesExist;
	}

}
