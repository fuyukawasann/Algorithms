package P052_거짓말쟁이가되긴싫어;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P052_거짓말쟁이가되긴싫어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N과 M을 받는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 배열 생성 및 parent 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 진실을 아는 사람들 처리
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int[] truth = new int[p];
        for (int i = 0; i < p; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
            union(truth[0], truth[i]);
        }

        // 각 파티 정보 저장
        ArrayList<Integer>[] parties = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            parties[i] = new ArrayList<>();

            int firstPerson = Integer.parseInt(st.nextToken());
            parties[i].add(firstPerson);

            for (int j = 1; j < num; j++) {
                int person = Integer.parseInt(st.nextToken());
                parties[i].add(person);
                union(firstPerson, person);
            }
        }

        // 거짓말할 수 있는 파티 수 계산
        int count = 0;
        int truthParent = find(truth.length > 0 ? truth[0] : 0);
        for (ArrayList<Integer> party : parties) {
            if (find(party.get(0)) != truthParent) {
                count++;
            }
        }

        System.out.println(count);
    }

    static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        if (pA != pB) {
            parent[pB] = pA;
        }
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}
