import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 스도쿠 {

	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] array;
	static boolean zero;
	static ArrayList<Pair> list = new ArrayList<Pair>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new int[10][10];

		for(int i=0;i<9;i++) {
			String str = br.readLine();
			for(int j=0;j<9;j++) {
				array[i][j] = str.charAt(j)-'0';
				if(array[i][j]==0) list.add(new Pair(i,j));
			}
		}

		dfs(0);
	}

	static void dfs(int index) {

		if(zero) return;

		if(index==list.size()) {

			zero=true;
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(array[i][j]);
				}
				System.out.println();
			}
			return;
		}

		int x = list.get(index).x;
		int y = list.get(index).y;

		for(int i=1;i<=9;i++) {
			if(check(x,y,i)) {
				array[x][y] = i;
				dfs(index+1);
				array[x][y] = 0;
			}
		}
	}

	static boolean check(int x, int y, int num) {

		for(int i=0;i<=9;i++) { // 가로, 세로 확인
			if(array[x][i]==num) return false;
			if(array[i][y]==num) return false;
		}

		for(int i=(x/3)*3;i<(x/3)*3+3;i++) { // 9칸 확인 
			for(int j=(y/3)*3;j<(y/3)*3+3;j++) {
				if(array[i][j]==num) return false;
			}
		}
		return true;
	}

}
