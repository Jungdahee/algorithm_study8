package week03_study;
import java.util.Scanner;

public class sum_number { // 수열의 합 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int N = sc.nextInt();
		int L = sc.nextInt(); // 적어도 L 이상의 개수 
		int start = 0; // 숫자의 시작 지점 
		int index = 0; // 
		// 나누는 수가 홀수인데 나머지 0이면, 나눈 몫이 가운데 자리
		// 나누는 수가 짝수면, 몫이 나머지자리에 위치함 
		for(int i=L;i<=100;i++) {
			sum = 0;
			if(i%2 == 1) { // 홀수
				if(N%i==0) {// 나눈 몫이 가운데 자리 
					start = (N/i)-(i/2);
					index = i; break;
				}
			}
			else { // 짝수 - 몫이 나머지 자리에 위치함 
				start = (N/i)-(N%i-1);
				int temp = start;
				for(int j=0;j<i;j++) {
					sum += start; start++;
				}
				if(sum == N) { start=temp; index=i; break; }
			}
		}
		
		if(start<0 || index == 0) {System.out.println(-1); return;}
		
		for(int i=0;i<index;i++) {
			System.out.print(start+" ");
			start++;
		}
	}
}
