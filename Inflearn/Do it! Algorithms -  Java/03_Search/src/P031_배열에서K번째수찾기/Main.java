package P031_배열에서K번째수찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P031_배열에서K번째수찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		long start = 1, end = M;
		long ans = 0;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long cnt = 0;
			
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}
			if(cnt < M) {
				start = mid + 1;
			}
			else {
				ans = mid;
				end = mid - 1;
			}
		}
		System.out.println(ans);
	}

}
