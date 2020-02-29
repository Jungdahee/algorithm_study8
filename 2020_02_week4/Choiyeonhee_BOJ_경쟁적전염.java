import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 경쟁적전염 {

	static class Pair{
		int x;
		int y;
		int time;
		public Pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int N,K,S,X,Y;
	static int[][] array;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int k;
	static Queue<Pair> queue = new LinkedList<Pair>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 바이러스 번호 1~K
		array = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];

		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // S초 후에 해당 위치에 바이러스 종류
		X = Integer.parseInt(st.nextToken()); // 존재하지 않는 다면 0
		Y = Integer.parseInt(st.nextToken());

		for(k=1;k<=K;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(array[i][j] == k && !visited[i][j]) {
						visited[i][j] = true;
						queue.offer(new Pair(i,j,0)); // 0초에 시작 
					}
				}
			}
		}
		bfs();
	}
	
	static void bfs() {
		
		while(!queue.isEmpty()) {
			
			Pair current = queue.poll();
			int x = current.x;
			int y = current.y;
			int time = current.time;
			if(time == S) break;
			
			for(int i=0;i<4;i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(1<=nx && nx<=N && 1<=ny && ny<=N && !visited[nx][ny]) {
					visited[nx][ny] = true;
					array[nx][ny] = array[x][y];
					queue.offer(new Pair(nx,ny,time+1));
				}
			}
		}
		System.out.println(array[X][Y]);
	}
}
