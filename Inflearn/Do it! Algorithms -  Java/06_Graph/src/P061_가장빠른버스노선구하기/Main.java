package P061_가장빠른버스노선구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 교재 예시
5
8
1 2 7
1 3 5
1 4 -3
3 4 3
3 5 3
4 2 3
4 5 6
5 3 2
*/

// G4
public class Main {
    
    // 최대 정점의 갯수 * 간선 당 최대 비용
    static final int INF = 100 * 100_000 + 1;
    
    // 정점 수, 간선 수
    static int N, M;

    // i->j 로 가는 최소 비용
    static int[][] dist;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P061_가장빠른버스노선구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for(int n=1; n<=N; n++){
            Arrays.fill(dist[n], INF);
            // 자기 자신으로의 거리는 0
            dist[n][n] = 0;
        }

        int a, b, c;
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // (조건)시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            // -> 최소 비용 간선만 가지고 있으면 된다.
            dist[a][b] = Math.min(dist[a][b], c);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(dist[i][j] == INF){
                    sb.append("0 ");
                }else{
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void floydWarshall(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    
                    // k를 거쳐가는 비용이 INF인 경우 의미없음
                    // dist[i][k] + dist[k][j] 에서의 오버플로우 방지용: INF를 문제에서 적절한 도달할 수 없는 값으로 하면 없어도 되는 부분
                    if(dist[i][k] == INF || dist[k][j] == INF){
                        continue;
                    }
                    // i->j 보다 i->k->j가 작으면 갱신
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

}
