package week03_study;
import java.util.Scanner;

public class Lotto {
	
	static int N;
	static int result = 0;
	static String[][] array;
	static String[] number;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int testcase=1;testcase<=T;testcase++) {
			result = 0; // 당첨금 
			N = sc.nextInt(); // 복권의 당첨번호 
			int M = sc.nextInt(); // M개의 고유번호 
			
			array = new String[N][N];
			number = new String[M]; // 고유번호 
			
			for(int i=0;i<N;i++) {
				array[i][0] = sc.next();
				array[i][1] = sc.next();
			}
			for(int i=0;i<M;i++) number[i] = sc.next();
			for(int i=0;i<M;i++) {
				check(i);
			}
			System.out.println("#"+testcase+" "+result);
		}
	}
	
	private static void check(int index) {
		
		for(int i=0;i<N;i++) {
			boolean value = true;
			for(int j=0;j<8;j++) {
				if(array[i][0].charAt(j)=='*') continue;
				else if(array[i][0].charAt(j)!=number[index].charAt(j)) value = false;
			}
			if(value==true) result+=Integer.parseInt(array[i][1]);
		}
		
	}

}
