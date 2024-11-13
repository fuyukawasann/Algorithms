package P2618;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N, W;
    static int[][] events;
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P2618/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        
        events = new int[W + 1][2];
        dp = new int[W + 1][W + 1];
        
        for (int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i <= W; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println(solve(0, 0));
        trace(0, 0);
    }
    
    static int solve(int car1, int car2) {
        int nextEvent = Math.max(car1, car2) + 1;
        if (nextEvent > W) {
            return 0;
        }
        
        if (dp[car1][car2] != -1) {
            return dp[car1][car2];
        }
        
        // car1이 다음 사건을 처리하는 경우
        int dist1 = car1 == 0 ? distance(1, 1, events[nextEvent][0], events[nextEvent][1])
                              : distance(events[car1][0], events[car1][1], events[nextEvent][0], events[nextEvent][1]);
        int moveCar1 = solve(nextEvent, car2) + dist1;
        
        // car2가 다음 사건을 처리하는 경우
        int dist2 = car2 == 0 ? distance(N, N, events[nextEvent][0], events[nextEvent][1])
                              : distance(events[car2][0], events[car2][1], events[nextEvent][0], events[nextEvent][1]);
        int moveCar2 = solve(car1, nextEvent) + dist2;
        
        return dp[car1][car2] = Math.min(moveCar1, moveCar2);
    }
    
    static void trace(int car1, int car2) {
        if (Math.max(car1, car2) == W) {
            return;
        }
        
        int nextEvent = Math.max(car1, car2) + 1;
        int dist1 = car1 == 0 ? distance(1, 1, events[nextEvent][0], events[nextEvent][1])
                              : distance(events[car1][0], events[car1][1], events[nextEvent][0], events[nextEvent][1]);
        int dist2 = car2 == 0 ? distance(N, N, events[nextEvent][0], events[nextEvent][1])
                              : distance(events[car2][0], events[car2][1], events[nextEvent][0], events[nextEvent][1]);
        
        if (dp[nextEvent][car2] + dist1 < dp[car1][nextEvent] + dist2) {
            System.out.println(1);
            trace(nextEvent, car2);
        } else {
            System.out.println(2);
            trace(car1, nextEvent);
        }
    }
    
    static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}