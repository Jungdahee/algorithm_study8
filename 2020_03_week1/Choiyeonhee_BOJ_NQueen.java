import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N_Queen {

	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N,count;
	static int[][] array;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		array = new int[N][N];

		for(int i=0;i<N;i++) {
			array[0][i] = 1;
			dfs(0,i);
			array[0][i] = 0;
		}

		System.out.println(count);
	}

	static void dfs(int x, int y) {

		if(x==N-1) {
			count++;
			return;
		}

		for(int i=0;i<N;i++) {

			if(check(x+1,i)) {
				array[x+1][i] = 1;
				dfs(x+1,i);
				array[x+1][i] = 0;
			}
			else continue;
		}
	}
	
	static boolean check(int x, int y) {
		
		// 세로 체크 
		for(int i=0;i<x;i++) {
			if(array[i][y]==1) return false;
		}
		
		// 왼쪽 대각선 체크 
		int temp_x = x-1;
		int temp_y = y-1;
		while(0<=temp_x && 0<=temp_y) {
			if(array[temp_x][temp_y]==1) return false;
			temp_x--;
			temp_y--;
		}
		
		// 오른쪽 대각선 체크 
		temp_x = x-1;
		temp_y = y+1;
		while(0<=temp_x && temp_y<N) {
			if(array[temp_x][temp_y]==1) return false;
			temp_x--;
			temp_y++;
		}
		
		return true;
	}
}

