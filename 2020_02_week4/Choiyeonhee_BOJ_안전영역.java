import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안전영역 {

	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] array;
	static boolean[][] visited;
	static int count,k;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	static int max = Integer.MIN_VALUE;
	static HashSet<Integer> hash = new HashSet<Integer>();
	static ArrayList<Integer> arr;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		visited = new boolean[N][N];

		hash.add(0); // 아무 지역도 물에 잠기지 않을 수도 있음
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				hash.add(array[i][j]);
			}
		}
		
		arr = new ArrayList<Integer>(hash);
		for(k=0;k<hash.size();k++) {
			
			visited = new boolean[N][N];
			count = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(array[i][j] > arr.get(k) && !visited[i][j]) { // 높이 일일이 다 세주기 
						bfs(i,j);
						count++;
					}
				}
			}
			max = Integer.max(max, count);
		}
		System.out.println(max);
	}

	static void bfs(int x, int y) {

		Queue<Pair> queue = new LinkedList<Pair>();
		queue.offer(new Pair(x,y));
		visited[x][y] = true;

		while(!queue.isEmpty()) {
			
			Pair current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0;i<4;i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<N && array[nx][ny] > arr.get(k) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new Pair(nx,ny));
				}
			}
		}
	}
}
