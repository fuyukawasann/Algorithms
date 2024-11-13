package P26216;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long sum = 1;
		long k = 50, h = 50;
		sum += (2*k + 1);
		long mul = k + 1;
		for(int i = 1; i <= 48; i++) {
			sum += (2*k+1) * mul;
			mul *= mul;
		}
		
		System.out.println(sum);
	}

}
