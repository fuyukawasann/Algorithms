package P11721;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception
	{
		System.setIn(new FileInputStream("src/P11721/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int idx = 0;
		int len = 0;
		StringBuilder sb = new StringBuilder();
		for(char c : br.readLine().toCharArray())
		{
			sb.append(c);
			idx++;
			len++;
			
			if(idx == 10)
			{
				idx = 0;
				sb.append("\n");
			}
		}
		
		// len가 10의 배수가 아니면 "\n"을 추가한다.
		if(len % 10 != 0) sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
}
