package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오목 {

	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] array;
	static boolean[][] visited;
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static int dy[] = {1,1,0,-1,-1,-1,0,1};
	static int count;
	static boolean check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new int[19][19];
		visited = new boolean[19][19];

		for(int i=0;i<19;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<19;j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		L:for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				count = 1;
				if(array[i][j]==1 || array[i][j]==2) {
					visited = new boolean[19][19];
					//if(visited[i][j]) continue;
					bfs(i,j,array[i][j]);
					if(check) break L;
				}
			}
		}
		if(!check) System.out.println(0);
	}

	static void bfs(int x, int y, int num) {

		Queue<Pair> queue = new LinkedList<Pair>();
		int start = x; int end = y;

		L:for(int i=0;i<8;i++) {
			count = 1;
			for(int j=0;j<6;j++) {
				queue.offer(new Pair(start,end));
				while(!queue.isEmpty()) {

					Pair current = queue.poll();
					x = current.x;
					y = current.y;

					if(count == 5) { // 승부가 결정된 경우 - 가장 왼쪽에 있는 바둑알 위치 

						int nx = x + dx[i]; // 끝에 하나 더 확인
						int ny = y + dy[i];

						if(0<=nx && nx<19 && 0<=ny && ny<19 && !visited[nx][ny]) {
							if(array[nx][ny]==num) {
								count++;
							}
						}
						// 뒤쪽에 하나 더확인 
						if(0<=start+dx[i]*(-1) && start+dx[i]*(-1)<19 
								&& 0<=end+dy[i]*(-1) && end+dy[i]*(-1)<19) {
						if(array[start+dx[i]*(-1)][end+dy[i]*(-1)]==num) count++;}
						
						if(count > 5) continue L;
						check = true;
						break L;
					}
					else if(count == 6) continue L;

					int nx = x + dx[i];
					int ny = y + dy[i];

					if(0<=nx && nx<19 && 0<=ny && ny<19 && !visited[nx][ny]) {
						if(array[nx][ny]==num) {
							count++;
							visited[nx][ny] = true;
							queue.offer(new Pair(nx,ny));
						}
					}
				}
			}
		}
		if(check) {
			System.out.println(num);
			if(end<=y) System.out.println((start+1)+" "+(end+1));
			else System.out.println((x+1)+" "+(y+1));
		}
	}
}
