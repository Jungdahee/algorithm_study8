package S06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_뱀 {

	static class Node {
		int r;
		int c;
		Node next;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Snake {
		Node head;
		Node tail;

		public Snake() {
			Node n = new Node(1, 1);
			head = n;
			tail = n;
		}

		public void move(int r, int c) {
			Node n = new Node(r, c);
			head.next = n;
			head = n;
			tail = tail.next;
		}

		public void moveWithApple(int r, int c) {
			Node n = new Node(r, c);
			head.next = n;
			head = n;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		// Input size of map and create map array
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];

		// Input the number of apples
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// Record the location of the apple on the map
			map[r][c] = -1;
		}

		// Input information of changing direction
		int L = Integer.parseInt(br.readLine());
		int[][] cd = new int[L + 1][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = (st.nextToken().charAt(0) == 'D') ? 1 : 2;

			cd[i][0] = x;
			cd[i][1] = d;
		}

		// Create snake object
		Snake snake = new Snake();

		// Record the location of the snake on the map
		map[1][1] = 1;

		// Direction of snake
		int dr = 0;
		int dc = 1;

		// Index for change direction array
		int idx = 0;

		int t = 1;
		while (true) {
			int hr = snake.head.r;
			int hc = snake.head.c;

			// move
			hr += dr;
			hc += dc;

			// If crush with wall
			if (hr <= 0 || hc <= 0 || hr > N || hc > N)
				break;

			// If crush with myself
			if (map[hr][hc] == 1)
				break;

			// If eat apple
			if (map[hr][hc] == -1) {
				map[hr][hc] = 1;
				snake.moveWithApple(hr, hc);
			}
			// If don't eat apple
			else {
				map[hr][hc] = 1;
				map[snake.tail.r][snake.tail.c] = 0;
				snake.move(hr, hc);
			}

			// If change direction
			if (cd[idx][0] == t) {
				// Right rotation
				if (cd[idx][1] == 1) {
					int dr2 = dc;
					int dc2 = -dr;
					dr = dr2;
					dc = dc2;
				}
				// Left rotation
				else {
					int dr2 = -dc;
					int dc2 = dr;
					dr = dr2;
					dc = dc2;
				}
				idx++;
			}

			t++;
		}

		bw.write(String.valueOf(t));

		bw.flush();
		bw.close();
		br.close();
	}

}
