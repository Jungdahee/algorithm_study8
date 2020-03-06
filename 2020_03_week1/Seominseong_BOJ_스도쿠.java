package S10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Seominseong_BOJ_스도쿠 {

	static class Cell {
		int r;
		int c;
		int pos;
		boolean[] disable;
		int count;

		public Cell(int r, int c, int pos) {
			this.r = r;
			this.c = c;
			this.pos = pos;

			disable = new boolean[10];
			count = 9;
		}
	}

	static class Queue {
		Cell[] cells;
		int pushIdx;
		int popIdx;

		Cell[] mem;
		int memPush;

		public Queue() {
			cells = new Cell[81];
			pushIdx = popIdx = 0;

			mem = new Cell[81];
			memPush = 0;
		}

		public boolean isEmpty() {
			return pushIdx == popIdx;
		}

		public void push(Cell c) {
			cells[pushIdx++] = c;
		}

		public Cell pop() {
			return cells[popIdx++];
		}

		public void save(Cell c) {
			mem[memPush++] = c;
		}

		public void reset() {
			cells = mem;
			pushIdx = memPush;

			memPush = 0;
			popIdx = 0;
		}
	}

	private static int len;
	private static Cell[] zeros;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[9][];
		for (int r = 0; r < 9; r++) {
			map[r] = br.readLine().toCharArray();
		}

		int[] rows = new int[9];
		int[] cols = new int[9];
		int[] cells = new int[9];

		Queue queue = new Queue();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				int pos = (r / 3) * 3 + (c / 3);
				if (map[r][c] != '0') {
					int bit = (1 << (map[r][c] - '0'));
					rows[r] |= bit;
					cols[c] |= bit;
					cells[pos] |= bit;
				} else {
					queue.push(new Cell(r, c, pos));
				}
			}
		}

		while (!queue.isEmpty()) {
			boolean check = true;
			while (!queue.isEmpty()) {
				Cell cell = queue.pop();

				int r = cell.r;
				int c = cell.c;
				int pos = cell.pos;
				boolean[] disable = cell.disable;

				// Check row, column, cell
				int numbers = (rows[r] | cols[c] | cells[pos]);
				for (int n = 1; n < 10; n++) {
					if (!disable[n]) {
						// Number n can't
						if ((numbers & (1 << n)) != 0) {
							disable[n] = true;
							cell.count--;
						}
					}
				}

				// Not found number
				if (cell.count > 1) {
					queue.save(cell);
					continue;
				}

				// If the number of possible numbers is one
				for (int n = 1; n < 10; n++) {
					if (!disable[n]) {
						check = false;

						map[r][c] = (char) ('0' + n);
						rows[r] |= (1 << n);
						cols[c] |= (1 << n);
						cells[pos] |= (1 << n);
						break;
					}
				}
			}

			queue.reset();

			// Not changed
			if (check)
				break;
		}

		zeros = queue.cells;
		len = queue.pushIdx;

		dfs(0);

		for (int i = 0; i < 9; i++) {
			bw.write(String.valueOf(map[i]) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean dfs(int n) {
		if (n == len) {
			return true;
		}

		int r = zeros[n].r;
		int c = zeros[n].c;
		int pos = zeros[n].pos;
		boolean[] disable = zeros[n].disable;
		for (int i = 1; i < 10; i++) {
			if (!disable[i]) {
				if (check(r, c, pos, i)) {
					map[r][c] = (char) ('0' + i);
					if (dfs(n + 1))
						return true;
				}
			}
		}

		map[r][c] = '0';
		return false;
	}

	private static boolean check(int r, int c, int pos, int n) {
		char ch = (char) ('0' + n);
		for (int mc = 0; mc < 9; mc++)
			if (map[r][mc] == ch)
				return false;

		for (int mr = 0; mr < 9; mr++)
			if (map[mr][c] == ch)
				return false;

		int rS = (pos / 3) * 3;
		for (int i = 0; i < 3; i++) {
			int cS = (pos % 3) * 3;
			for (int j = 0; j < 3; j++) {
				if (map[rS][cS] == ch)
					return false;
				cS++;
			}
			rS++;
		}

		return true;
	}

}
