package week03_study;
import java.util.Scanner;

public class sum_number { // ������ �� 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int N = sc.nextInt();
		int L = sc.nextInt(); // ��� L �̻��� ���� 
		int start = 0; // ������ ���� ���� 
		int index = 0; // 
		// ������ ���� Ȧ���ε� ������ 0�̸�, ���� ���� ��� �ڸ�
		// ������ ���� ¦����, ���� �������ڸ��� ��ġ�� 
		for(int i=L;i<=100;i++) {
			sum = 0;
			if(i%2 == 1) { // Ȧ��
				if(N%i==0) {// ���� ���� ��� �ڸ� 
					start = (N/i)-(i/2);
					index = i; break;
				}
			}
			else { // ¦�� - ���� ������ �ڸ��� ��ġ�� 
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
