package P076_이항계수구하기1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dp = new int[11][11];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P076_이항계수구하기1/input.txt"));
		int N, K;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(combination(N, K));
		
	}

	
	public static int combination(int n, int k)
	{
		if(n == k || k == 0) return 1;
		else if(dp[n][k] != 0) {
			return dp[n][k];
		}
		else {
			return dp[n][k] = combination(n-1, k-1) + combination(n-1,k);
		}
		
	}

}
