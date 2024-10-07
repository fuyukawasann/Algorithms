package P7568;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// 파일 입출력
		System.setIn(new FileInputStream("src/P7568/input.txt"));
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		People[] people = new People[N];
		int[] score = new int[N];
		Arrays.fill(score, 1);
		
		// 사람의 정보를 받는다.
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			people[i] = new People(w, h);
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				// 자기 자신은 건너 뛴다.
				if(i == j) continue;
				
				// 만약 i가 지시하는 사람의 덩치가 더 크다면 (키, 몸무게 모두 클 때)
				// score를 증가한다.
				if(people[j].weight > people[i].weight && people[j].height > people[i].height)
				{
					score[i]++;
				}
			}
		}
		
		// score을 출력한다.
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			sb.append(score[i]).append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();

	}

}

class People
{
	int weight, height;
	
	public People(int weight, int height)
	{
		this.weight = weight;
		this.height = height;
	}
}