package S08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Seominseong_BOJ_오목 {

	private static char[][] map;
	private static int[][] dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[21][21];
		dir = new int[21][21];

		String str;
		for (int i = 1; i <= 19; i++) {
			str = br.readLine();
			for (int j = 1, idx = 0; j <= 19; j++, idx += 2) {
				map[i][j] = str.charAt(idx);
			}
		}

		boolean check = false;
		int i = 1, j = 1;
		L: for (; i <= 19; i++) {
			for (j = 1; j <= 19; j++) {
				if (map[i][j] != '0') {
					if (search(i, j)) {
						check = true;
						break L;
					}
				}
			}
		}

		if (d == 3) {
			i = r;
			j = c;
		}

		char ch = (check) ? map[i][j] : '0';

		bw.write(String.valueOf(ch));

		if (check) {
			bw.newLine();
			bw.write(String.valueOf(i) + " " + String.valueOf(j));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int[] di = { 0, 1, 1, 1 };
	private static int[] dj = { 1, 1, 0, -1 };
	private static int d;
	private static int r, c;

	private static boolean search(int i, int j) {
		char ch = map[i][j];

		for (d = 0; d < 4; d++) {
			if ((dir[i][j] & (1 << d)) != 0)
				continue;

			int mi = i + di[d];
			int mj = j + dj[d];

			int count = 1;
			while (map[mi][mj] == ch) {
				dir[mi][mj] |= (1 << d);
				mi += di[d];
				mj += dj[d];
				count++;
			}

			if (d == 3) {
				r = mi - 1;
				c = mj + 1;
			}

			if (count == 5)
				return true;
		}

		return false;
	}

}
