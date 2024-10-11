package S2024.��ݱ�.����.P002;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	
	static int Q, N, M;
	static boolean isAvailableExist;
	static int[] dist;
	
	
	static ArrayList<Edge>[] arrList;
	static Item[] list = new Item[30_000 + 1];
	static boolean[] isAvailable;
	static PriorityQueue<sItem> ansList = new PriorityQueue<>();
	static ArrayList<Integer> idSave = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// ���� �Է�
		System.setIn(new FileInputStream("src/S2024/��ݱ�/����/P002/input.txt"));
		
		// ���̺귯��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// ������ ������ �޴´�.
		Q = Integer.parseInt(br.readLine());
		
		// ���� ��ǰ ����
		
		StringBuilder sb = new StringBuilder();
		for(int q = 1; q <= Q; q++)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			
			// ���� �Ǽ�
			if(op == 100)
			{
				// N�� M�� �޴´�.
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				
				arrList = new ArrayList[N];
				dist = new int[N];
				
				// �迭 �ʱ�ȭ
				for(int i = 0; i < N; i++)
				{
					arrList[i] = new ArrayList<>();
				}
				
				// ������ �޴´�.
				for(int m = 0; m < M; m++)
				{
					int u = Integer.parseInt(st.nextToken());
					int v = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					
					// ������̴ϱ� ��� ����
					arrList[u].add(new Edge(v, w));
					arrList[v].add(new Edge(u, w));
				}
				
				// ���ͽ�Ʈ�� �����Ѵ�.
				dijkstra(0); // ������ �������� 0��
				isAvailableExist = false;
				isAvailable = new boolean[30_000 + 1];
			}
			// ���� ��ǰ ����
			else if(op == 200)
			{
				// ��� �������� �߰��Ѵ�.
				int id = Integer.parseInt(st.nextToken());
				int rev = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				
				// ����Ʈ�� �߰�
				list[id] = new Item(id, rev, dest);
				// idSave�� ����
				idSave.add(id);
				// pq�� �߰�
				// dist�� INF �̰ų� cost > rev �̸� �Ǹ� �Ұ�
				if(dist[dest] == INF || dist[dest] > rev)
				{
					isAvailable[id] = true;
					continue;
				}
				
				// �� �ܿ��� ansList�� �߰�
				ansList.offer(new sItem(id, rev - dist[dest]));
				isAvailableExist = true;
				isAvailable[id] = true;
				
			}
			
			// ���� ��ǰ ���
			else if(op == 300)
			{
				int id = Integer.parseInt(st.nextToken());
				
				list[id] = null;
				isAvailable[id] = false;
				
			}
			// ������ �����ǰ �Ǹ�
			else if(op == 400)
			{
				// ����Ʈ�� �ִ� �͵�� dist�� �̿��� ��� (���⼭ dist�� cost ��)
				
				// ���� �Ǹ� ������ �� ������ -1 ���
				if(!isAvailableExist) sb.append(-1).append("\n");
				else
				{
					boolean isExist = false;
					
					while(!isExist)
					{
						// pq�� ������ Ż����;;
						if(ansList.isEmpty()) break;
						// pq���� �ϳ� ����
						sItem answer = ansList.poll();
						// id�� ������ ���� �ִ��� Ȯ��
						if(isAvailable[answer.id])
						{
							// �����Ѵٸ� ������ �Է�
							sb.append(answer.id).append("\n");
							// isExist�� ������ �ٲ�
							isExist = true;
							// ��ܿ��� ����
							list[answer.id] = null;
							isAvailable[answer.id] = false;
						}
					}
					
					if(!isExist) sb.append(-1).append("\n");
				}
			}
			// ����� ����
			else
			{
				int start = Integer.parseInt(st.nextToken());
				dijkstra(start);
				
				// ���ͽ�Ʈ��� �ٲ����ϱ� �����۵� �ٽ� �ֱ�
				isAvailableExist = false;
				boolean[] newIsAvailable = new boolean[30_000 + 1];
				// �ϴ� ansList�� ���
				ansList.clear();
				
				ArrayList<Integer> newIdSave = new ArrayList<>();
				
				for(int i = 0; i < idSave.size(); i++)
				{
					// ��� �ִ��� Ȯ��
					if(!isAvailable[idSave.get(i)]) continue;
					
					// �� �ܿ��� newIdSave�� ����
					newIdSave.add(idSave.get(i));
					
					// �Ǹ� �Ұ��ϸ� �������� �ǳʶ�
					if(dist[list[idSave.get(i)].dest] == INF || dist[list[idSave.get(i)].dest] > list[idSave.get(i)].revenue)
					{
						newIsAvailable[list[idSave.get(i)].id] = true;
						continue;
					}
					
					// �� �ܿ��� ansList�� �߰�
					ansList.offer(new sItem(list[idSave.get(i)].id, list[idSave.get(i)].revenue - dist[list[idSave.get(i)].dest]));
					isAvailableExist = true;
					newIsAvailable[list[idSave.get(i)].id] = true;
					
				}
				
				// ����
				isAvailable = newIsAvailable;
				idSave = newIdSave;
			}
				
		}
		
		// ���� �Է�
		bw.write(sb.toString());
		
		// ���� ���
		bw.flush();
		
		
		// �ڿ� ��ȯ
		bw.close();
		br.close();

	}
	
	
	static void dijkstra(int start)
	{
		// �켱���� ť�� �����Ѵ�.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// dist�� INF�� ä���.
		Arrays.fill(dist, INF);
		
		// �������� dist�� 0���� �����Ѵ�.
		dist[start] = 0;
		
		// pq�� �������� �ִ´�.
		pq.offer(new Edge(start, dist[start]));
		
		while(!pq.isEmpty())
		{
			// ť���� ������
			Edge now = pq.poll();
			
			// �̹� ������Ʈ �� �Ŷ�� �ǳʶ�
			if(dist[now.to] < now.weight) continue;
			
			// �װ� �ƴ϶�� ���� ����Ʈ�� ��ȸ��
			for(Edge next : arrList[now.to])
			{
				// dist ������Ʈ�� �ʿ��ϴٸ� ������Ʈ
				if(dist[next.to] > now.weight + next.weight)
				{
					dist[next.to] = now.weight + next.weight;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
		
	}

}

class sItem implements Comparable<sItem>
{
	int id, revCost;
	
	public sItem(int id, int revCost)
	{
		this.id = id;
		this.revCost = revCost;
	}

	@Override
	public int compareTo(sItem o) {
		// TODO Auto-generated method stub
		// ���� revCost�� ��������
		int dRC = Integer.compare(o.revCost, this.revCost);
		
		// �����Ѱ� �ִٸ� id�� ���� ���� ���
		if(dRC == 0) return Integer.compare(this.id, o.id);
		else return dRC;
	}
}

class Item
{
	int id, revenue, dest;
	
	public Item(int id, int revenue, int dest)
	{
		this.id = id;
		this.revenue = revenue;
		this.dest = dest;
	}
}

class Edge implements Comparable<Edge>
{
	int to, weight;
	
	public Edge(int to, int weight)
	{
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.weight, o.weight);
	}
}
