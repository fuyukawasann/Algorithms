package P057_최소비용구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // dist 초기화 위한 최댓값 설정
    static final int INF = 100_000 * 1000 + 1; // 최대 비용의 최대치 계산
    static int N, M;

    // 인접 리스트를 만든다.
    static ArrayList<Edge>[] adjList;
    static int[] dist;

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/P057_최소비용구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            dist[i] = INF;
        }

        int from, to, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        dijkstra(start);
        sb.append(dist[dest]).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            // 현재 노드가 이미 처리된 최단 경로라면 무시
            if (dist[current.to] < current.cost) continue;

            for (Edge next : adjList[current.to]) {
                if (dist[next.to] > dist[current.to] + next.cost) {
                    dist[next.to] = dist[current.to] + next.cost;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }
}
