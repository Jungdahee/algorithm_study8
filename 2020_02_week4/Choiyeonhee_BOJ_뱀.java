import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class �� {
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x ;
			this.y = y;
		}
	}

	static int N,apple,dir;
	static int[][] map;
	static int[] time;
	static char[] direction;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N  = Integer.parseInt(br.readLine());
		apple = Integer.parseInt(br.readLine()); // ��� ���� 
		map = new int[N+1][N+1];
		map[1][1] = 1; // ���� ������ġ 

		for(int i=0;i<apple;i++) { // ����� ��ġ 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 2; // ��� ��ġ 
		}

		dir = Integer.parseInt(br.readLine()); // ���� ��ȯ Ƚ��
		time = new int[dir];
		direction = new char[dir];

		for(int i=0;i<dir;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			direction[i] = st.nextToken().charAt(0);
		}
		
		position(1,1,0);
		
		System.out.println(endtime);
	}

	static int dx[] = {0,-1,0,1}; // �� �� �� �� 
	static int dy[] = {1,0,-1,0};
	static int endtime;
	
	static void position(int x, int y, int direct) {
		
		Queue<Pair> queue = new LinkedList<Pair>();
		
		queue.offer(new Pair(x,y));
		
		int row = x;
		int col = y;
		endtime = 1;
		
		while(true) {
			
			row += dx[direct];
			col += dy[direct];
			
			if(row>N || row<1 || col>N || col<1) break; // ���� �ε�������
			
			if(map[row][col]==1) break; // �ڽ��� ���� �ε������� 
			
			if(map[row][col]==2) { // ��� �Ծ�����
				queue.offer(new Pair(row,col));
				map[row][col] = 1;
			}
			
			if(map[row][col]==0) { // ����� ������
				queue.offer(new Pair(row,col));
				map[row][col] = 1;
				Pair a = queue.poll();
				map[a.x][a.y] = 0;
			}
			
			for(int i=0;i<dir;i++) {
				if(endtime==time[i]) {
					direct = dirchange(direction[i],direct);
				}
			}
			endtime++;
		}
	}
	
	static int dirchange(char dir, int ndir) {
		
		switch(dir) {
		case 'D':
			if(ndir==0) return 3;
			else return ndir-1;
		
		case 'L':
			if(ndir==3) return 0;
			else return ndir+1;
		}
		return ndir;
	}
}

