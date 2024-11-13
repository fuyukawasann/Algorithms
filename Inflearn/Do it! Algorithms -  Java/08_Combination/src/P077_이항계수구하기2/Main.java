package P077_이항계수구하기2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, K;
	public static int[][] dp = new int[1001][1001];
	public static int STANDARD = 10007;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P077_이항계수구하기2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(combination(N, K));
	}
	
	public static int combination(int n, int r)
	{
		if(n == r || r == 0)
		{
			return 1;
		}
		else if(dp[n][r] != 0)
		{
			return dp[n][r];
		}
		else {
			return dp[n][r] = (combination(n-1, r-1) + combination(n-1, r)) % STANDARD;
		}
	}

}
