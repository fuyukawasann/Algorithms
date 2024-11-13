package P059_타임머신으로빨리가기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 타임머신 G4
public class Main {
    static class Edge{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static long[] dist;
    static ArrayList<Edge> adjList;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P059_타임머신으로빨리가기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new long[N+1];
        adjList = new ArrayList<>();

        int A, B, C;
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            adjList.add(new Edge(A, B, C));
        }

        StringBuilder sb = new StringBuilder();
        if(bellmanFordMoore(N, 1)){
            sb.append("-1\n");
        }else{
            for(int i=2; i<=N; i++){
                if (dist[i] == Long.MAX_VALUE) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean bellmanFordMoore(int V, int start){
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        // V-1번 E개의 모든 간선 확인
        for(int i=0; i<V-1; i++){
            for(Edge edge : adjList){
                // 간선의 시작점이 아직 탐색 불가면 continue
                if(dist[edge.from] == Long.MAX_VALUE){
                    continue;
                }

                // 최단거리 갱신
                if(dist[edge.to] > dist[edge.from] + edge.cost){
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }


        boolean isNegativeCycle = false;
        // V번째 E개의 모든 간선을 확인해서 갱신되는 구간이 있으면 음의 사이클이 존재하는 것
        for(Edge edge : adjList){
            if(dist[edge.from] == Long.MAX_VALUE){
                continue;
            }

            if(dist[edge.to] > dist[edge.from] + edge.cost){
                isNegativeCycle = true;
                break;
            }
        }

        return isNegativeCycle;
    }

}