package P2817;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/P2817/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// X�� N�� �޴´�.
		int X = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		// �迭 ����
		boolean[] isPass = new boolean[26];
		int[] chips = new int[26];
		
		// 5% ���ؼ��� ���Ѵ�.
		double threshold = (double)X * 0.05;
		
		PriorityQueue<Staff> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			char name = st.nextToken().charAt(0);
			int gainScore = Integer.parseInt(st.nextToken());
			
			// 5% ��ǥ �̻����� Ȯ��
			if(gainScore < threshold) continue;
			
			isPass[name - 'A'] = true;
			// ������ 1 ~ 14�� ���� ��������
			for(int j = 1; j <= 14; j++)
			{
				pq.offer(new Staff(name, (double)gainScore / j));
			}
		}
		
		// pq���� 14�� ������ Ĩ�� ���
		for(int i = 1; i <= 14; i++)
		{
			Staff now = pq.poll();
			
			chips[now.name - 'A']++;
		}
		
		// ������ �Է�
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
