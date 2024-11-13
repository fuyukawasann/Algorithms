package P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int N, K;
    static boolean[] visited;
    static String[] words;
    static int selectedCoount = 0;
    static int max = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/P1062/input.txt"));
        Scanner sc = new Scanner(System.in);

        visited['a' - 'a'] = true;
        visited['n'-'a'] = true;
        visited['t'-'a'] = true;
        visited['i'-'a'] = true;
        visited['c'-'a'] = true;

        for (int i = 0; i < 26; i++) {
            if(visited[i]==false) {
                dfs(i);
            }
        }
    }

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        selectedCoount++;

        // 2. 목적지인가? - 선택한 단어의 개수가 K개 인가?
        if(selectedCoount == K) {
            // 읽을 수 있는 단어개수 파악.
            max = Math.max(max, countWords());
        }
        else {
            // 3. 연결된 곳을 순회 - 현재보다 다음거 ~ Z
            for(int next = index + 1; next < 26; next++) {
                // 4. 갈 수 있는가? - 방문한 적이 없으면
                if (visited[next] == false) {
                    // 5. 간다 - dfs 호출
                    dfs(next);
                }
            }
        }
        // 6. 체크아웃
        visited[index] = false;
        selectedCoount--;
    }

    static int countWords() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            boolean isPossible = true;
            String word = words[i];
            for(int j = 0; j < word.length(); j++)
            {
                if (visited[word.charAt(j) - 'a'] == false) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible == true) {
                count++;
            }
        }
        return count;
    }
}