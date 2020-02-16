import java.util.Scanner;
import java.util.Stack;

public class 백준15685_드래곤커브 {

	static int N;
	static int x,y; // 시작지점
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1}; // 방향
	static int dir = 0; // 방향 
	static int state = 0; // 세대 
	static int[][] array;
	static int result = 0; // 정사각형 개수 
	
	static int check_dx[] = {0,1,1,0}; // 사각형 체크 방향 
	static int check_dy[] = {0,0,1,1};

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 드래곤 커브 개수 
		array = new int[101][101];

		for(int i=0;i<N;i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = sc.nextInt();
			state = sc.nextInt();
			move(); // 드래곤 움직여 주기 
		}
		// 사각형 체크해주기 
		check();
		System.out.println(result); 
	}

	static void move() { // 좌표, 방향, 세대 넘어옴 
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		stack.push(dir); // 0세대는 기본으로 넣어주기 
		array[x][y] = 1; // 시작점
		// 0세대 기본으로 움직여주기 
		if(dir==0) array[++x][y] = 1;
		else if(dir==1) array[x][--y] = 1;
		else if(dir==2) array[--x][y] = 1;
		else if(dir==3) array[x][++y] = 1;
	
		while(true) {
			temp = (Stack<Integer>)stack.clone();
			if(state == 0) break;
			while(!temp.isEmpty()) {

				if(temp.peek()==0) { // 바로 직전이 오른쪽 -> 위
					temp.pop();
					stack.push(1);
					array[x][--y] = 1;
				}
				else if(temp.peek()==1) { // 바로 직전이 위쪽 -> 왼쪽
					temp.pop();
					stack.push(2);
					array[--x][y] = 1;
				}
				else if(temp.peek()==2) { // 바로 직전이 왼쪽 -> 아래
					temp.pop();
					stack.push(3);
					array[x][++y] = 1;
				}
				else if(temp.peek()==3) { // 바로 직전이 아래쪽 -> 오른쪽
					temp.pop();
					stack.push(0);
					array[++x][y] = 1;
				}
			}
			state--;
		}
	}

	static void check() {
		
		for(int i=0;i<=100;i++) {
			L:for(int j=0;j<=100;j++) {
				
				boolean value = true;
				for(int k=0;k<4;k++) { // 정사각형 꼭기점이 모두 지나갔는지 체크 
					
					int nx = i + check_dx[k];
					int ny = j + check_dy[k];
					if(0<=nx && nx<=100 && 0<=ny && ny<=100) {
						if(array[nx][ny]!=1) value = false;
					}
					else continue L;
				}
				if(value == true) result++;
			}
		}
	}
}



