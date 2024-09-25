package P17070;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] map;
    static int[][][] dp; // dp[r][c][d]: (r, c) 위치에서 d 방향으로 올 수 있는 방법의 수

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P17070/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3]; // 3방향: 가로(0), 세로(1), 대각선(2)

        // 지도 입력 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태 설정: (0, 1)에 가로 방향 파이프가 시작
        dp[0][1][0] = 1;

        // DP 테이블 채우기
        for (int r = 0; r < N; r++) {
            for (int c = 1; c < N; c++) {
                if (map[r][c] == 1) continue; // 벽일 경우 넘어감

                // 가로 방향
                if (c > 0) {
                    dp[r][c][0] += dp[r][c-1][0]; // 가로에서 가로로
                    dp[r][c][0] += dp[r][c-1][2]; // 대각선에서 가로로
                }

                // 세로 방향
                if (r > 0) {
                    dp[r][c][1] += dp[r-1][c][1]; // 세로에서 세로로
                    dp[r][c][1] += dp[r-1][c][2]; // 대각선에서 세로로
                }

                // 대각선 방향
                if (r > 0 && c > 0 && map[r-1][c] == 0 && map[r][c-1] == 0) {
                    dp[r][c][2] += dp[r-1][c-1][0]; // 가로에서 대각선으로
                    dp[r][c][2] += dp[r-1][c-1][1]; // 세로에서 대각선으로
                    dp[r][c][2] += dp[r-1][c-1][2]; // 대각선에서 대각선으로
                }
            }
        }

        // 마지막 칸(N-1, N-1)에 도달하는 방법의 수 출력
        int result = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
        System.out.println(result);
    }
}