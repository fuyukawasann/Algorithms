package P5052;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    static class TrieNode {
        // 현재 노드의 자식 노드들
        TrieNode[] children = new TrieNode[10];
        boolean isEndOfNumber; // 이 노드가 어떤 번호의 끝인지 여부

        public TrieNode() {
            isEndOfNumber = false;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 전화번호를 트라이에 삽입
        public boolean insert(String phoneNumber) {
            TrieNode node = root;
            for (int i = 0; i < phoneNumber.length(); i++) {
                int digit = phoneNumber.charAt(i) - '0'; // 각 문자를 숫자로 변환
                if (node.children[digit] == null) {
                    node.children[digit] = new TrieNode();
                }
                node = node.children[digit];

                // 중간 노드가 번호의 끝이면 다른 번호가 이 번호의 접두어인 경우
                if (node.isEndOfNumber) {
                    return false;
                }
            }

            // 새로운 번호를 추가하는 중에, 다른 번호가 이 번호의 접두어인지 확인
            node.isEndOfNumber = true;
            for (int i = 0; i < 10; i++) {
                if (node.children[i] != null) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/P5052/input.txt"));
    	
    	
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // 테스트 케이스의 수

        for (int test = 0; test < t; test++) {
            int n = sc.nextInt();  // 전화번호의 수
            String[] phoneNumbers = new String[n];
            Trie trie = new Trie();

            boolean isConsistent = true;
            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = sc.next();
            }

            // 전화번호 목록을 트라이에 하나씩 추가하며 일관성 확인
            for (int i = 0; i < n; i++) {
                if (!trie.insert(phoneNumbers[i])) {
                    isConsistent = false;
                    break;
                }
            }

            if (isConsistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}