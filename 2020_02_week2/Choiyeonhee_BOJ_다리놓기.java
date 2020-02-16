import java.util.Scanner;

public class 백준1010_다리놓기 {
	
	static boolean[] visited;
	static int n, r;
	static int result;
	static int[][] sum;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sum = new int[31][31];
		
		for(int i=0;i<T;i++) {
			
			r = sc.nextInt();
			n = sc.nextInt();
			System.out.println(combination(n,r));
		}
	}
	
	static int combination(int n, int r){
		
		if(sum[n][r]!=0) return sum[n][r];
		else if(n==r || r==0) return 1;
		else sum[n][r] = combination(n-1,r-1)+combination(n-1,r);
		return sum[n][r];
	}
}
