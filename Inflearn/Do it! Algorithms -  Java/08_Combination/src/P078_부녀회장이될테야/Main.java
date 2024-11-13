package P078_부녀회장이될테야;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	
	static int dp[][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P078_부녀회장이될테야/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// max K, N -> 14
		dp = new int[15][15];
		makeDP();
		
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(dp[K][N]);
		}
	}
	
	static void makeDP() {
		// start at 0
		for(int i = 0; i <= 14; i++) {
			if(i == 0) {
				for(int j = 0; j <= 14; j++) {
					dp[i][j] = j;
				}
			}
			else {
				for(int j = 1; j <= 14; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
				}
				
			}
		}
	}

}
