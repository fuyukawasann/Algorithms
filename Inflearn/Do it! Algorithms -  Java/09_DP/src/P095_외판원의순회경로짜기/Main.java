package P095_외판원의순회경로짜기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
3
0 1 3
2 0 5
4 6 0

4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

5
0 1 1 1 1
1 0 1 1 1
1 1 0 1 1
1 1 1 0 1
1 1 1 1 0

6
0 1 1 1 1 1
1 0 1 1 1 1
1 1 0 1 1 1
1 1 1 0 1 1
1 1 1 1 0 1
1 1 1 1 1 0
*/
public class Main {
    static int N;
    static final int INF = 16_000_000;
    static int[][] W, dp;
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P095_외판원의순회경로짜기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N+1][N+1];
        dp = new int[(1<<N)][N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
                // 자기자신으로 가는 길 일때는 W[i][i] = 0 (문제의 조건)
            }
        }

        for(int i=1; i<=(1<<N)-1; i++){
            // 계산해본적 없는 곳은 -1로 표시
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));

        // print2DArrayWithIndices(dp);
        br.close();
    }

    static int dfs(int current, int visited){
        // 탈출조건 : 1111111111이면 bottom이므로 리턴
        if(visited == (1<<N)-1){
            // 현재지점 -> 시작지점(1번노드)
            if(W[current][1] == 0) return INF;
            else return W[current][1];
        }

        // 탈출조건 : 이미 dp 값을 구해놓은 곳이면 리턴
        if(dp[visited][current] != -1){
            // System.out.println("dp["+Integer.toBinaryString(visited)+"]["+current+"] = "+dp[visited][current]);
            return dp[visited][current];
        }
        // 해당 도시는 계산해봤음을 표시
        dp[visited][current] = INF;

        for(int next=1; next<=N; next++){
            int nextIndex = 1<<(next-1);
            // current -> i : i번 노드를 아직 방문하지 않았고 가는 길이 있을 경우
            if((visited & nextIndex) == 0 && W[current][next] != 0){
                dp[visited][current] = Math.min(dp[visited][current], W[current][next] + dfs(next, visited|nextIndex));
            }
        }

        return dp[visited][current];
    }

    static void print2DArrayWithIndices(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        // Print column indices
        System.out.print("     ");
        for (int col = 0; col < cols; col++) {
            System.out.printf("%4d", col);
        }
        System.out.println();

        // Print separator line
        System.out.print("    ");
        for (int col = 0; col < cols; col++) {
            System.out.print("----");
        }
        System.out.println();

        // Print rows with row indices
        for (int row = 0; row < rows; row++) {
            // System.out.printf("%2d |", row); // Print row index
            System.out.printf("%3s |", Integer.toBinaryString(row)); // Print row index
            for (int col = 0; col < cols; col++) {
                System.out.printf("%4d", array[row][col]); // Print array element
            }
            System.out.println();
        }
    }
}
