package P2108;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P2108/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] howMany = new int[8000 + 1];
		int sum = 0;
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> hash = new HashSet<>();
		int manyTime = 0;
		
		for(int i = 0; i < N; i++)
		{
			nums[i] = Integer.parseInt(br.readLine());
			sum += nums[i];
			howMany[nums[i] + 4000]++;
			// 만약 지금 최빈값과 같다면
			if(manyTime == howMany[nums[i] + 4000])
			{
				// 기존에 추가했는지 확인하고 없을 때만 추가
				if(!hash.contains(nums[i])) {
					list.add(nums[i]);
					hash.add(nums[i]);
				}
			}
			// 만약 지금 최빈값보다 크다면
			if(manyTime < howMany[nums[i] + 4000])
			{
				manyTime = howMany[nums[i] + 4000];
				hash.clear();
				list.clear();
				list.add(nums[i]);
				hash.add(nums[i]);
			}
		}
		
		// 정답 입력
		StringBuilder sb = new StringBuilder();
		
		// 산술평균
		int avg = (int) Math.round((double)sum / N);
		sb.append(avg).append("\n");
		// 중앙값
		Arrays.sort(nums);
		sb.append(nums[N / 2]).append("\n");
		// 최빈값
		Collections.sort(list);
		if(list.size() == 1) sb.append(list.get(0)).append("\n");
		else sb.append(list.get(1)).append("\n");
		// 범위
		sb.append(nums[N - 1] - nums[0]).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

}
