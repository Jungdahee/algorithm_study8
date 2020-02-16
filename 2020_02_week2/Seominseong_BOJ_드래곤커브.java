package S04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class DragonCurve {

	static boolean[][] map = new boolean[101][101];
	static int[][] coordinates = new int[10000][2];
	static int left, top, right, bottom;

	// direction
	// 0 : increase x(right)
	// 1 : decrease y(up)
	// 2 : decrease x(left)
	// 3 : increase y(down)
	static final int[] dx = { 1, 0, -1, 0 };
	static final int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		// Make map
		left = top = 101;
		right = bottom = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			makeDragonCurve(x, y, d, g);
		}

		// Count number of squares
		int result = 0;
		for (int x = left; x < right; x++) {
			for (int y = top; y < bottom; y++) {
				if (map[x][y] && map[x + 1][y] && map[x + 1][y + 1] && map[x][y + 1]) {
					result++;
				}
			}
		}

		// Print result on standard output
		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	private static void setScope(int x, int y) {
		if (left > x)
			left = x;
		if (right < x)
			right = x;
		if (top > y)
			top = y;
		if (bottom < y)
			bottom = y;
	}

	private static void makeDragonCurve(int x, int y, int d, int g) {
		int idx = 0;
		// add start coordinate
		coordinates[idx][0] = x;
		coordinates[idx][1] = y;
		idx++;

		setScope(x, y);

		// set end of dragon curve
		int f_x = x + dx[d];
		int f_y = y + dy[d];

		setScope(f_x, f_y);

		// set map
		map[x][y] = true;
		if (f_x >= 0 && f_y >= 0 && f_x <= 100 && f_y <= 100) {
			map[f_x][f_y] = true;
		}

		// f.x, f.y : coordinate of end of dragon curve
		// x' = -y + f.x + f.y
		// y' = x - f.x + f.y
		// set from 1-generation dragon curve to g-generation dragon curve
		for (int i = 1; i <= g; i++) {
			int count = 0;
			for (int j = 0; j < idx; j++) {
				// get dragon curve coordinate
				int ox = coordinates[j][0];
				int oy = coordinates[j][1];

				// rotate coordinate to 90 degree
				int mx = -oy + f_x + f_y;
				int my = ox - f_x + f_y;

				// set map if it possible
				if (mx >= 0 && my >= 0 && mx <= 100 && my <= 100) {
					map[mx][my] = true;
					setScope(mx, my);
				}

				// add rotated coordinate to coordinates array
				coordinates[idx + count][0] = mx;
				coordinates[idx + count][1] = my;
				count++;
			}
			idx += count;

			// add current end of dragon curve coordinate to coordinates array
			coordinates[idx][0] = f_x;
			coordinates[idx][1] = f_y;
			idx++;

			// set new end of dragon curve
			int tx = f_x;
			int ty = f_y;
			f_x = -y + tx + ty;
			f_y = x - tx + ty;
		}
	}

}
