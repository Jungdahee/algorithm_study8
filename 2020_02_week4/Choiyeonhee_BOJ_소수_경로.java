import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소수_경로 {

	static class Pair{
		int x;
		int time;
		public Pair(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	static int start,end;
	static boolean[] visited;
	static boolean[] prime;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); 
		prime = new boolean[10000];
		eratos();

		for(int i=0;i<N;i++) {
			visited = new boolean[10000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			bfs(start,0);
		}
	}

	static void eratos() { // 소수 아닌것이 true

		prime[0] = true;
		prime[1] = true;

		for(int i=2;i*i<10000;i++) {
			for(int j=i*i;j<10000;j+=i) {
				prime[j] = true;
			}
		}
	}

	static void bfs(int x, int time) {

		Queue<Pair> queue = new LinkedList<Pair>();
		visited[x] = true;
		queue.offer(new Pair(x,0));
		int temp = 0;

		while(!queue.isEmpty()) {

			Pair current = queue.poll();
			x = current.x;
			time = current.time;
			if(x==end) break;

			if(x>=1000 && x<=9999) {
				
				for(int i=0;i<4;i++) {
					for(int j=0;j<=9;j++) {
						if(i==0 && j==0) continue;
						
						temp = change(x,i,(char)(j+'0'));
						if(!visited[temp] && !prime[temp]) {
							visited[temp] = true;
							queue.offer(new Pair(temp,time+1));
						}
					}
				}
			}
		}
		if(x==end) System.out.println(time);
		else System.out.println(0);
	}
	
	static int change(int num, int i, char j) {
		StringBuilder sb = new StringBuilder(String.valueOf(num));
		sb.setCharAt(i, j);
		return Integer.parseInt(sb.toString());
	}
}
