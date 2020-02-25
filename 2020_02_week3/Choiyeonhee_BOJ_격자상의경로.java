import java.util.Scanner;

public class 백준10164_격자상의경로 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M =sc.nextInt();
		int[][] array = new int[N][M];
		int value = sc.nextInt(); // 표시되어 있는 칸 
		int x = 0; int y = 0;

		if(value != 0) {
			x = value / M;
			y = value % M -1;
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {

				if(i==0 || j==0) array[i][j] = 1; // 1행 
				if(1<=i && 1<=j) array[i][j] = array[i-1][j] + array[i][j-1];
				if(value != 0) {if(i == x && j == y) break;}
			}
		}

		if(value != 0) {
			for(int i=x;i<N;i++) {
				for(int j=y;j<M;j++) {
					if(1<=i && 1<=j) { // 범위 체크 
					if(i==x || j==y) array[i][j] = array[x][y];
					else array[i][j] = array[i-1][j] + array[i][j-1];}
				}
			}
		}
		
		System.out.println(array[N-1][M-1]);
	}
}
