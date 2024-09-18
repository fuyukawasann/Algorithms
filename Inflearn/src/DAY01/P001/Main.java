package DAY01.P001;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/DAY01/P001/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] char1, char2;
		String prefix;
		
		// 초기 설정
		prefix = br.readLine();
		char1 = prefix.toCharArray();
		
		for(int i = 2; i <= N; i++) {
			// 문자를 받는다.
			String input = br.readLine();
			// char1과 비교한다.
			char2 = input.toCharArray();
			// 공통 접두어의 길이가 짧아지면 공통 접두어 prefix를 업데이트한다.
			int idx = 0;
			while(idx < char1.length && idx < char2.length) {
				// 만약 두 문자가 같다면 continue
				if(char1[idx] == char2[idx]) {
					idx++;
					continue;
				}
				// 다르면 break
				break; // 공통 접두어만 볼 것이기 때문
			}
			// 두 문자가 달라버리면 길이 비교
			// 기존 prefix보다 길이가 짧을 때만 prefix를 업데이트
			if(prefix.length() > idx + 1) {
				prefix = input.substring(0, idx);
			}
			
			// char1을 char2로 설정한다.
			char1 = char2;
		}
		
		// 접두어를 출력한다.
		System.out.println(prefix);

	}

}
