package P1697;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	public int position;		// 현재 지점
	public int time;			// 소비 시간

	public Node (int position, int time) {
		this.position = position;
		this.time = time;
	}

	public int compareTo(Node n) {
		return this.time - n.time;
	}
}

public class Main {
	static int n, k;				// 수빈이의 시작 지점 n, 목표 동생 지점 k
	static int minTime;				// 출력, 최소 시간

	static final int MAX_POSITION = 100_000;
	static final int INF = 100_000;
	static int[] time = new int[MAX_POSITION + 1];
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static void dijkstra() {
		// 비용 배열, 우선순위 큐 초기화
		Arrays.fill(time, INF);
		time[n] = 0;
		pq.add(new Node(n, 0));

		while (!pq.isEmpty()) {
			Node current = pq.remove();

			// 이미 갱신된 time[] 은 제외
			if (time[current.position] < current.time)
				continue;

			int np1 = current.position - 1;
			// 현재까지 갱신된 최적 경로로 다음 지점 도달 최소 시간 time[np1]
			// > 현재 새로 탐색하는 경로로 다음 지점 도달 시간 current.time + 1
			if (isValid(np1) && time[np1] > current.time + 1) {
				time[np1] = current.time + 1;
				pq.add(new Node(np1, time[np1]));
			}

			int np2 = current.position + 1;
			if (isValid(np2) && time[np2] > current.time + 1) {
				time[np2] = current.time + 1;
				pq.add(new Node(np2, time[np2]));
			}

			int np3 = current.position * 2;
			if (isValid(np3) && time[np3] > current.time) {
				time[np3] = current.time;			// x 2 순간 이동은 시간 그대로
				pq.add(new Node(np3, time[np3]));
			}
		}
	}

	static boolean isValid(int position) {
		return 0 <= position && position <= MAX_POSITION;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if (n >= k)
			minTime = n - k;	// -1 칸씩 n-k 번 이동하는 한 가지
		else {
			dijkstra();
			minTime = time[k];
		}

		System.out.println(minTime);
	}
}