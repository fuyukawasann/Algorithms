package P4673;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 라이브러리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		boolean[] isSelfNumber = new boolean[10_000 + 1];
		Arrays.fill(isSelfNumber, true);
		
		
		for(int i = 1; i <= 10_000; i++)
		{
			// false면 continue
			if(!isSelfNumber[i]) continue;
			
			// 아니면 지금 숫자를 sb에 넣는다.
			sb.append(i).append("\n");
			
			// 그 뒤의 생성자를 기반으로 하는 숫자들을 넣는다.
			int now = i;
			// next = now + now의 각 자리 숫자
			int next = now;
			while(next <= 10_000)
			{
				isSelfNumber[next] = false;
				while(now >= 10) {
					next += now % 10;
					now /= 10;
				}
				next += now;
				// 만든 next를 다음 생성자로 지정한다.
				now = next;
			}
		}
		
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
		

	}

}
