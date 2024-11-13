package P022_수정렬하기3;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P022_수정렬하기3/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(A);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(A[i]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
