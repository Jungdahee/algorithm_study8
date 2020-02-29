import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ³óÀå°ü¸® {
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M;
	static int[][] array;
	static boolean[][] visited;
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static int dy[] = {1,1,0,-1,-1,-1,0,1};
	static int count;
	static int max;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		HashSet<Integer> hash = new HashSet<Integer>();
		
		array = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(array[i][j]>0 && !visited[i][j]) {
					bfs(i,j);
				}
			}
		}
		
		System.out.println(count);
	}
	
	static void bfs(int x, int y) {
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.offer(new Pair(x,y));
		
		while(!queue.isEmpty()) {
			
			Pair current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0;i<8;i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					if(array[nx][ny] > array[x][y]) return;
				}
			}
			
			visited[x][y] = true;
			
			for(int i=0;i<8;i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					if(array[nx][ny]==array[x][y] && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.offer(new Pair(nx,ny));
					}
				}
			}
		}
		count++;
	}
}
