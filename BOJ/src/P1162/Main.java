package P1162;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final long INF = Long.MAX_VALUE;

    static int N, M, K;
    static ArrayList<Edge>[] adjList;
    static long[][] dist;

    static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State implements Comparable<State> {
        int node, freeRoads;
        long cost;

        public State(int node, long cost, int freeRoads) {
            this.node = node;
            this.cost = cost;
            this.freeRoads = freeRoads;
        }

        @Override
        public int compareTo(State o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P1162/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        dist = new long[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, cost));
            adjList[to].add(new Edge(from, cost));
        }

        dijkstra(1);

        long result = INF;
        for (int i = 0; i <= K; i++) {
            result = Math.min(result, dist[N][i]);
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(start, 0, 0));
        dist[start][0] = 0;

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.cost > dist[current.node][current.freeRoads]) continue;

            for (Edge next : adjList[current.node]) {
                // 무료로 사용하지 않은 경우
                if (current.cost + next.cost < dist[next.to][current.freeRoads]) {
                    dist[next.to][current.freeRoads] = current.cost + next.cost;
                    pq.offer(new State(next.to, dist[next.to][current.freeRoads], current.freeRoads));
                }

                // 무료로 사용하는 경우
                if (current.freeRoads < K && current.cost < dist[next.to][current.freeRoads + 1]) {
                    dist[next.to][current.freeRoads + 1] = current.cost;
                    pq.offer(new State(next.to, dist[next.to][current.freeRoads + 1], current.freeRoads + 1));
                }
            }
        }
    }
}