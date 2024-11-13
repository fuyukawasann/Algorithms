package P11000;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P11000/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[][] lectures = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken());
            lectures[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 강의 시작 시간으로 정렬
        Arrays.sort(lectures, (a, b) -> a[0] - b[0]);
        
        // 우선순위 큐를 사용하여 끝나는 시간을 관리
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0][1]);
        
        for (int i = 1; i < n; i++) {
            // 현재 강의의 시작 시간과 가장 빨리 끝나는 강의의 끝 시간을 비교
            if (lectures[i][0] >= pq.peek()) {
                pq.poll();  // 강의실을 다시 사용 가능하므로 제거
            }
            pq.add(lectures[i][1]);
        }
        
        // 우선순위 큐의 크기가 필요한 강의실의 개수
        System.out.println(pq.size());
    }
}