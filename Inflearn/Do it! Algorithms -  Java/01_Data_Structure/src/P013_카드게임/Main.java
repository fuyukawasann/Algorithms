package P013_카드게임;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P013/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayDeque<Integer> queue = new ArrayDeque<>();

		// 큐 세팅
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}

		// 마지막 하나가 남을 때까지 실행
		while (queue.size() != 1) {
			// 앞의 수를 제거한다.
			queue.poll();

			// 두 번째 수를 제거하고 마지막에 넣는다.
			queue.offer(queue.poll());
		}

		// 남은 하나의 수를 출력한다.
		System.out.println(queue.poll());

	}

}
