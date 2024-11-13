package P005_나머지합구하기;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static long[] nums;
    static long[] modCount;

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P005_나머지합구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N, M 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // nums 배열 초기화
        nums = new long[N + 1];
        modCount = new long[M];  // 나머지 값을 세기 위한 배열
        
        // 입력 받고 누적 합 계산
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = (nums[i-1] + Integer.parseInt(st.nextToken())) % M;
            modCount[(int)nums[i]]++;  // 나머지 값 증가
        }
        
        // 첫 번째 요소에 대한 카운트 추가 (nums[0] % M == 0 인 경우 포함)
        modCount[0]++;

        long cnt = 0;

        // 같은 나머지를 가진 쌍의 개수 계산
        for (int i = 0; i < M; i++) {
            if (modCount[i] > 1) {
                cnt += (modCount[i] * (modCount[i] - 1)) / 2;  // 조합 공식 nC2
            }
        }
        
        // 정답을 출력
        System.out.println(cnt);
    }
}