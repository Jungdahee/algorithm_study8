import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class XY{
	int x; 
	int y;
	int dist;
	int door;
	XY(int x, int y, int dist, int door){
		this.x = x;
		this.y = y;
		this.dist = dist; // 이동거리
		this.door = door; // 공사횟수
	}
}

public class 백준2206_벽부수고이동 {

	static int N,M;
	static int[][] array;
	static int[][] visited;
	static int dx[] = {0,-1,0,1};
	static int dy[] = {1,0,-1,0};
	static int ans, door;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		array = new int[N][M];
		visited = new int[N][M]; // 0,1,2 관리 -> int형
		// 벽 뚫은 곳은 2, 이미 지나간 곳은 1

		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				array[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs(0,0);

		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	static void bfs(int x, int y) {

		Queue<XY> queue = new LinkedList<XY>();
		visited[x][y] = 0;

		queue.offer(new XY(0,0,1,0)); // 시작하는 칸도 세줌

		while(!queue.isEmpty()) {

			XY current = queue.poll();

			x = current.x;
			y = current.y;
			ans = current.dist;
			door = current.door;

			if(x==N-1 && y==M-1) { min = ans; break;}

			for(int k=0;k<4;k++) {

				int nx = x + dx[k];
				int ny = y + dy[k];

				if(0<=nx && nx<N && 0<=ny && ny<M) {
					
					if(door==0) {
						if(visited[nx][ny] == 1) continue;
					}
					else { // 벽 한번 뚫었을때
						if(visited[nx][ny]!=0) continue;
					}
					
					if(array[nx][ny]==0) { // 벽이 아닐때
						queue.offer(new XY(nx,ny,ans+1,door));
						if(door == 0) visited[nx][ny] = 1;
						else if(door == 1) visited[nx][ny] = 2;
					}
					else { // 벽일때 
						if(door==0) { // 벽 한번만 뚫을 수 있다.
							queue.offer(new XY(nx,ny,ans+1,door+1));
							visited[nx][ny] = 2;
						}
					}
				}
			}
		}
	}
}
