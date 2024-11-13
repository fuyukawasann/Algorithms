package P024_신기한소수찾기;

import java.io.*;


public class Main {
	
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P024_신기한소수찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		DFS(2, 1);
		DFS(3, 1);
		DFS(5, 1);
		DFS(7, 1);

	}
	
	static void DFS(int number, int place) {
		if(place == N) {
			if(isPrime(number)) {
				System.out.println(number);
			}
			return;
		}
		for(int i = 1; i < 10; i++) {
			if(i % 2 == 0) { // 짝수는탐색 필요 없음
				continue;
			}
			if (isPrime(number * 10 + i)) {
				DFS(number * 10 + i, place + 1);
			}
		}
	}
	
	static boolean isPrime(int num) {
		for(int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
