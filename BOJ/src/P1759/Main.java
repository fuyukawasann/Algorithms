package P1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int L, C;
    static char[] data;
    static List<String> result;
    static StringBuilder sb; // Builder가 더 빠르다.
    // StringBuffer는 스레드니퍼 기능이 있다.

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        Arrays.sort(data); // 정렬하는 함수

        dfs(-1, 0, 0);
        // for문을 사용했다면 자음, 모음을 정해야 하기 때문에 귀찮더라도 current에 -1을 추가한 것이다.
        // 자음, 모음이 아니라 조건이 복잡하다면, 다른 곳에서 실수했을 때 크게 올 수 있다.

    }

    static void dfs(int current, int con, int vow) {
        if(current >= 0) {
            // 1. 체크인: 현재까지 만들어진 암호
            sb.append(data[current]);
        }
        // 2. 목적지인가: length == L, 자음, 모음 조건 -> 암호 조건
        if(sb.length() == L) {
            if(con >= 2 && vow >= 1) {
                result.add(sb.toString());
            }
        }
        else {
            // 3. 연결된 곳을 순회 : current  + 1 ~ C
            for(int i = current + 1; i < C; i++) {
                // 4. 갈 수 있는가?(생략)
                // 5. 간다: 자음, 모음
                if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u') {
                    dfs(i, con, vow + 1);
                }
                else {
                    dfs(i, con + 1, vow);
                }

            }
        }
        if (current >= 0) {
            // 6. 체크아웃: 현재까지 만들어진 암호
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}