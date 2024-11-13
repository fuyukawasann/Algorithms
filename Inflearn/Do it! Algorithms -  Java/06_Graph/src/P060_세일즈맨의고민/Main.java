package P060_세일즈맨의고민;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Edge[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/P060_세일즈맨의고민/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken()); // 노드의 수
        int start = Integer.parseInt(stk.nextToken()); // 시작도시
        int destination = Integer.parseInt(stk.nextToken()); // 도착 도시
        int m = Integer.parseInt(stk.nextToken()); // 에지의 수

        graph = new Edge[m + 1];
        long[] dist = new long[n + 1];

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());
            graph[i] = new Edge(a, b, w);
        }
        long [] money = new long[n+1];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++){
            money[i] = Integer.parseInt(stk.nextToken());
        }

        // 벨만 포드 알고리즘
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[start] = money[start];

        for(int i = 0 ; i <= n+100; i++){
            for(int j = 0 ; j < m; j++){
                Edge now_edge = graph[j];
                if(dist[now_edge.st]==Long.MIN_VALUE)
                    continue;
                else if(dist[now_edge.st]==Long.MAX_VALUE){
                    dist[now_edge.end] = Long.MAX_VALUE;
                }
                else if(dist[now_edge.end] < dist[now_edge.st]+money[now_edge.end]-now_edge.cost){
                    dist[now_edge.end] = dist[now_edge.st]+money[now_edge.end]-now_edge.cost;
                    if(i>=n)
                        dist[now_edge.end] = Long.MAX_VALUE;
                }
            }
        }
        if(dist[destination] == Long.MIN_VALUE)
            bw.write("gg\n");
        else if(dist[destination] == Long.MAX_VALUE)
            bw.write("Gee\n");
        else bw.write(dist[destination]+"\n");
        bw.flush();
        bw.close();
    }

    static class Edge {
        int st;
        int end;
        int cost;

        public Edge(int s, int e, int c) {
            this.st = s;
            this.end = e;
            this.cost = c;
        }
    }
}