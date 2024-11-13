package P081_순열의순서구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long K;
	static boolean[] num;
	static long[] fact = new long[21];
	static List<Integer> permutation = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P081_순열의순서구하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 1 ~ N 순열
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 모드 입력
		int calcMod = Integer.parseInt(st.nextToken());

		if (calcMod == 1) {
			// N 자리 수가 어떻게 채워지는지 확인한다.
			K = Long.parseLong(st.nextToken());
			int number = 0;
			// 미리 list에 N까지 넣어둔다.
			for (int i = 0; i <= N; i++) {
				permutation.add(i);
			}

			int n = N;
			for (int i = 0; i < N; i++) {
				if (i == N - 1) {
					sb.append(permutation.get(permutation.size() - 1));
					break;
				}
				number = (int) Math.ceil((double) K / factorial(n - 1));
				sb.append(permutation.get(number));
				permutation.remove(number);
				sb.append(' ');
				// K를 업데이트
				K -= factorial(n-1)*(number - 1);
				K = K % factorial(n - 1);
				if(K == 0) K = factorial(n-1);
				n--;
			}

		} else {
			// 아니면 모드 2를 실행합니다.
			num = new boolean[N+1];
			num[0] = true;
			int n = N;
			long idx = 1;
			for(int i = 0; i < N - 1; i++)
			{
				int nums = Integer.parseInt(st.nextToken());
				idx += searchIndex(nums, n--);
			}
			sb.append(idx);
			sb.append(' ');
		}
		sb.append('\n');

		// 결과를 출력한다.
		System.out.println(sb.toString());

	}
	
	static long searchIndex(int number, int n)
	{
		long cnt = 0;
		for(int i = 1; i <= number; i++)
		{
			if(!num[i]) cnt++;
		}
		num[number] = true;
		
		return (cnt - 1) * factorial(n - 1);
	}

	public static long factorial(int n) {
		if (n == 1) {
			return 1;
		} else if (fact[n] != 0) {
			return fact[n];
		} else {
			return n * factorial(n - 1);
		}
	}

}
