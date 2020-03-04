

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회장뽑기 {

	static class Pair{
		int x;
		int dist;
		public Pair(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}
	}
	
	static int N,k,count,i,max;
	static int[][] array;
	static boolean[] visited;
	static int[] temp;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 회원 수 
		array = new int[N+1][N+1];
		temp = new int[N+1];
		int result = 0;

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start==-1 && end==-1) break;
			else {
				array[start][end] = 1;
				array[end][start] = 1;
			}
		}

		for(int i=1;i<=N;i++) {
			visited = new boolean[N+1];
			max = Integer.MIN_VALUE;
			bfs(i);
			min = Math.min(min, temp[i]);
		}

		System.out.print(min+" ");
		for(int i=1;i<=N;i++) {
			if(min==temp[i]) {
				result++;
			}
		}
		System.out.println(result);

		for(int i=1;i<=N;i++) {
			if(min==temp[i]) {
				System.out.print(i+" ");
			}
		}
	}

	static void bfs(int x) {
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.offer(new Pair(x,0));
		visited[x] = true;
		int dist = 0;
		
		while(!queue.isEmpty()) {
			
			Pair current = queue.poll();
			int nx = current.x;
			dist = current.dist;
			max = Math.max(max, dist);
			
			for(int i=1;i<=N;i++) {
				if(array[nx][i]==1 && !visited[i]) {
					visited[i] = true;
					queue.offer(new Pair(i,dist+1));
				}
			}
		}
		temp[x] = dist;
	}
}
