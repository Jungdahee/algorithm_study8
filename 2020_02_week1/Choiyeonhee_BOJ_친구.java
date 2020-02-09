package week03_study;
import java.util.Scanner;

public class friend {
	
	static int  N;
	static char[][] array;
	static boolean[] visited;
	static int result;
	static int max = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		array = new char[N][N];
		visited = new boolean[N];
		result = 0;
		
		for(int i=0;i<N;i++) {
			String str = sc.next();
			for(int j=0;j<N;j++) {
				array[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0;i<N;i++) {
			result = 0;
			for(int j=0;j<N;j++) {
				if(i==j) continue; // �ڽŰ� ģ���� �ƴϴ�
				if(array[i][j] == 'Y') result++; // �ٷ� 2-ģ�� 
				else friend(i,j); // 2-ģ������ Ȯ���ϱ� 
				if(max < result) max = result;
			}
		}
		System.out.println(max);
	}
	private static void friend(int x, int y) {
		for(int j=0;j<N;j++) {
			if(array[x][j] == 'Y') {
				if(array[j][y] == 'Y') { result++; break; }
			}
		}
	}

}
