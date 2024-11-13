package P17471;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static List<Integer>[] adj;
    static boolean[] selected;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P17471/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        adj = new List[N + 1];
        selected = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                adj[i].add(neighbor);
                adj[neighbor].add(i);
            }
        }

        // 모든 가능한 조합을 검사
        subset(1);

        System.out.println(minDifference == Integer.MAX_VALUE ? -1 : minDifference);
    }

    static void subset(int idx) {
        if (idx == N + 1) {
            // 모든 구역이 선택된 후, 선거구의 유효성을 체크
            if (isConnected()) {
                calculateDifference();
            }
            return;
        }

        // 현재 구역을 선거구 A에 포함
        selected[idx] = true;
        subset(idx + 1);

        // 현재 구역을 선거구 B에 포함
        selected[idx] = false;
        subset(idx + 1);
    }

    static boolean isConnected() {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                A.add(i);
            } else {
                B.add(i);
            }
        }

        if (A.size() == 0 || B.size() == 0) return false; // A나 B가 비어있으면 무효

        boolean[] visited = new boolean[N + 1];
        // 선거구 A의 연결성 확인
        bfs(A.get(0), visited, true);

        // 선거구 B의 연결성 확인
        bfs(B.get(0), visited, false);

        // 모든 구역이 방문되었는지 확인
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    static void bfs(int start, boolean[] visited, boolean flag) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : adj[curr]) {
                if (!visited[neighbor] && selected[neighbor] == flag) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    static void calculateDifference() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                sumA += population[i];
            } else {
                sumB += population[i];
            }
        }

        int difference = Math.abs(sumA - sumB);
        minDifference = Math.min(minDifference, difference);
    }
}