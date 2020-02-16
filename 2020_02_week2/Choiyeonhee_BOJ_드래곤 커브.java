import java.util.Scanner;
import java.util.Stack;

public class ����15685_�巡��Ŀ�� {

	static int N;
	static int x,y; // ��������
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1}; // ����
	static int dir = 0; // ���� 
	static int state = 0; // ���� 
	static int[][] array;
	static int result = 0; // ���簢�� ���� 
	
	static int check_dx[] = {0,1,1,0}; // �簢�� üũ ���� 
	static int check_dy[] = {0,0,1,1};

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // �巡�� Ŀ�� ���� 
		array = new int[101][101];

		for(int i=0;i<N;i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = sc.nextInt();
			state = sc.nextInt();
			move(); // �巡�� ������ �ֱ� 
		}
		// �簢�� üũ���ֱ� 
		check();
		System.out.println(result); 
	}

	static void move() { // ��ǥ, ����, ���� �Ѿ�� 
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		stack.push(dir); // 0����� �⺻���� �־��ֱ� 
		array[x][y] = 1; // ������
		// 0���� �⺻���� �������ֱ� 
		if(dir==0) array[++x][y] = 1;
		else if(dir==1) array[x][--y] = 1;
		else if(dir==2) array[--x][y] = 1;
		else if(dir==3) array[x][++y] = 1;
	
		while(true) {
			temp = (Stack<Integer>)stack.clone();
			if(state == 0) break;
			while(!temp.isEmpty()) {

				if(temp.peek()==0) { // �ٷ� ������ ������ -> ��
					temp.pop();
					stack.push(1);
					array[x][--y] = 1;
				}
				else if(temp.peek()==1) { // �ٷ� ������ ���� -> ����
					temp.pop();
					stack.push(2);
					array[--x][y] = 1;
				}
				else if(temp.peek()==2) { // �ٷ� ������ ���� -> �Ʒ�
					temp.pop();
					stack.push(3);
					array[x][++y] = 1;
				}
				else if(temp.peek()==3) { // �ٷ� ������ �Ʒ��� -> ������
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
				for(int k=0;k<4;k++) { // ���簢�� �������� ��� ���������� üũ 
					
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



