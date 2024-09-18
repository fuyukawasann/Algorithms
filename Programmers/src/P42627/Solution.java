package P42627;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P42627/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// jobs를 만든다.
		int[][] jobs = new int[3][2];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			jobs[i][0] = Integer.parseInt(st.nextToken());
			jobs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 여기부터가 프로그래머스 부분
		int answer = 0;
		int end = 0; // 수행되고난 직후의 시간
		int jobsIdx = 0; // jobs 배열의 인덱스
		int count = 0; // 수행된 요청 갯수

		// 원본 배열 오름차순 정렬 (요청시간 오름차순)
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		// 처리 시간 오름차순으로 정렬되는 우선순위 큐(Heap)
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		// 요청이 모두 수행될 때까지 반복
		while (count < jobs.length) {

			// 하나의 작업이 완료되는 시점(end)까지 들어온 모든 요청을 큐에 넣음
			while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
				pq.add(jobs[jobsIdx++]);
			}

			// 큐가 비어있다면 작업 완료(end) 이후에 다시 요청이 들어온다는 의미
			// (end를 요청의 가장 처음으로 맞춰줌)
			if (pq.isEmpty()) {
				end = jobs[jobsIdx][0];

			// 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행
			} else {

				int[] temp = pq.poll();
				answer += temp[1] + end - temp[0];
				end += temp[1];
				count++;
			}
		}		
		
		// 결과 출력
		System.out.println((int) Math.floor(answer / jobs.length));
	}

}