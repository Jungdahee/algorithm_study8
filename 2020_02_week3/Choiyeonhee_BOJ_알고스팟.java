import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*class Pair{
	int x;
	int y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}*/

public class 백준1261_알고스팟 {

	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static int N,M;
	static boolean[][] visited;
	static int[][] array;
	static int[][] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		array = new int[N][M];
		dist = new int[N][M];

		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				array[i][j] = str.charAt(j) - '0';
			}
		}
		bfs(0,0);
	}

	static void bfs(int x, int y) {

		Deque<Pair> queue = new LinkedList<Pair>();

		queue.addLast(new Pair(x, y));
		visited[x][y] = true;

		while(!queue.isEmpty()) {

			Pair current = queue.pollLast();
			x = current.x;
			y = current.y;

			for(int i=0;i<4;i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny]) {
				
					if(array[nx][ny]==0) { // 0일때 
						dist[nx][ny] = dist[x][y];
						queue.addLast(new Pair(nx,ny));
						visited[nx][ny] = true;
					}
					else { // 0이 아닐때 
						dist[nx][ny] = dist[x][y] + 1;
						queue.addFirst(new Pair(nx,ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		System.out.println(dist[N-1][M-1]);
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) System.out.print(dist[i][j]);
			System.out.println();
		}
	}

}
