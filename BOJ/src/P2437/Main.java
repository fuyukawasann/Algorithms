package P2437;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		// 파일 읽기
		System.setIn(new FileInputStream("src/P2437/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// N을 받는다.
		N = Integer.parseInt(br.readLine());
		
		// 숫자를 받는다.
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 정렬
		Arrays.sort(nums);
		
		
		int result = 1;
		
		for (int i = 0; i < N; i++) {
            // 만들 수 없는 무게를 찾음
            if (nums[i] > result) {
                break;
            }
            result += nums[i];
        }
		
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		bw.write(sb.toString());
		bw.close();
		
		
		// 자원 반환
		bw.close();
		br.close();

	}

}
