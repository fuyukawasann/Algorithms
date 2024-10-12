package P2817;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입력
		System.setIn(new FileInputStream("src/P2817/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// X와 N을 받는다.
		int X = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		// 배열 생성
		boolean[] isPass = new boolean[26];
		int[] chips = new int[26];
		
		// 5% 기준선을 정한다.
		double threshold = (double)X * 0.05;
		
		PriorityQueue<Staff> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			char name = st.nextToken().charAt(0);
			int gainScore = Integer.parseInt(st.nextToken());
			
			// 5% 득표 이상인지 확인
			if(gainScore < threshold) continue;
			
			isPass[name - 'A'] = true;
			// 점수는 1 ~ 14로 나눈 나머지임
			for(int j = 1; j <= 14; j++)
			{
				pq.offer(new Staff(name, (double)gainScore / j));
			}
		}
		
		// pq에서 14개 꺼내서 칩을 배분
		for(int i = 1; i <= 14; i++)
		{
			Staff now = pq.poll();
			
			chips[now.name - 'A']++;
		}
		
		// 정답을 입력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 26; i++)
		{
			if(!isPass[i]) continue;
			
			sb.append((char)(i + 'A')).append(" ").append(chips[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}

class Staff implements Comparable<Staff>
{
	char name;
	double score;
	
	public Staff(char name, double score)
	{
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Staff o) {
		// TODO Auto-generated method stub
		return Double.compare(o.score, this.score);
	}
}
