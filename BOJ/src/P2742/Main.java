package P2742;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// File Input
		System.setIn(new FileInputStream("src/P2742/input.txt"));
		
		// Set N
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int n = N; n >= 1; n--)
		{
			sb.append(n).append("\n");
		}
		
		// Output
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}
