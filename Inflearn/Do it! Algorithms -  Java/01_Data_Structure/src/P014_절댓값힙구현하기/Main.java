package P014_절댓값힙구현하기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P014/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			// 0이 아니면 pq에 값을 넣는다.
			if (x != 0) {
				if (x >= 0) {
					pq.offer(new Node(x, x));
				} else {
					pq.offer(new Node(-x, x));
				}
			}
			// 0이면 값을 출력
			else {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else sb.append(pq.poll().value).append("\n");
			}
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static class Node implements Comparable<Node> {
		int abs, value;

		public Node(int abs, int value) {
			this.abs = abs;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			int dAbs = Integer.compare(this.abs, o.abs);

			if (dAbs == 0)
				return Integer.compare(this.value, o.value);
			return dAbs;
		}
	}

}
