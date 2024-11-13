package P13334;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P13334/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] intervals = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
            intervals[i][0] = start;
            intervals[i][1] = end;
        }
        
        int d = Integer.parseInt(br.readLine());
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // 끝점 기준으로 정렬
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCount = 0;
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            if (end - start <= d) {  // 철로의 길이보다 작거나 같은 선분만 고려
                pq.offer(start);
            }
            
            while (!pq.isEmpty() && pq.peek() < end - d) {
                pq.poll();  // 철로 길이 내에 포함되지 않는 선분 제거
            }
            
            maxCount = Math.max(maxCount, pq.size());
        }
        
        System.out.println(maxCount);
    }
}