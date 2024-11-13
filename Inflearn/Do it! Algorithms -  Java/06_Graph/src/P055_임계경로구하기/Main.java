package P055_임계경로구하기;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Edge>[] adjList, reverseAdjList;
    static long[] time;
    static int[] indegree;
    static boolean[] visited;

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P055_임계경로구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // N과 M을 받는다.
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 배열 선언
        adjList = new ArrayList[N + 1];
        reverseAdjList = new ArrayList[N + 1];
        time = new long[N + 1];
        indegree = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }

        // 도로를 받는다.
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, cost));
            reverseAdjList[to].add(new Edge(from, cost)); // 역방향 간선 저장
            indegree[to]++;
        }

        // 시작점, 끝점을 받는다.
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int maxTime = topologicalSort(start, end);
        int criticalPathCount = countCriticalPaths(end);

        StringBuilder sb = new StringBuilder();
        sb.append(maxTime).append("\n").append(criticalPathCount).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int topologicalSort(int start, int end) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        time[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge next : adjList[current]) {
                if (time[next.to] < time[current] + next.cost) {
                    time[next.to] = time[current] + next.cost;
                }

                indegree[next.to]--;

                if (indegree[next.to] == 0) {
                    queue.offer(next.to);
                }
            }
        }
        return (int) time[end];
    }

    static int countCriticalPaths(int end) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(end);
        visited[end] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge prev : reverseAdjList[current]) {
                if (time[prev.to] + prev.cost == time[current]) {
                    count++;
                    if (!visited[prev.to]) {
                        visited[prev.to] = true;
                        queue.offer(prev.to);
                    }
                }
            }
        }
        return count;
    }
}