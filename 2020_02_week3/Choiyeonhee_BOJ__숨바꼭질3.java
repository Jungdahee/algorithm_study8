import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position{
	int x;
	int y;
	Position(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class 백준13549_숨바꼭질3 {
	
	static int N,K;
	static boolean[] visited;
	static int ans;
	static int dx[] = {1,-1,1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		
		bfs(N,0);
		
	}
	
	static void bfs(int N, int dist) {
		
		Queue<Position> queue = new LinkedList<Position>();
		queue.offer(new Position(N,0));
		visited[N] = true;
		int nx = 0;
		
		while(!queue.isEmpty()) {
			
			Position current = queue.poll();
			N = current.x;
			ans = current.y;
			if(N==K) break;
			
			for(int k=0;k<3;k++) {
				if(k==0) nx = N*2;
				else nx = N + dx[k];
				
				if(0<=nx && nx<=100000 && !visited[nx]) {
					visited[nx] = true;
					if(k==0) queue.offer(new Position(nx,ans));
					else queue.offer(new Position(nx,ans+1));
				}
			}
		}
		System.out.println(ans);
	}
}
