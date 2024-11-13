package P040_제곱이아닌수찾기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/P040_제곱이아닌수찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long Min = Long.parseLong(st.nextToken());
		long Max = Long.parseLong(st.nextToken());
		
		boolean[] Check = new boolean[(int) (Max - Min + 1)];
		
		for(long i = 2; i * i <= Max; i++) {
			long pow = i * i;
			long start_idx = Min / pow;
			if(Min % pow != 0) {
				start_idx++;
			}
			for(long j = start_idx; pow * j <= Max; j++) {
				Check[(int) ((j * pow) - Min)] = true;
			}
		}
		
		int count = 0;
		for(int i = 0; i <= Max - Min; i++) {
			if(!Check[i]) {
				count++;
			}
		}
		
		System.out.println(count);
	}

}
