package S07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seominseong_BOJ_농장관리 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;

	private static Queue<int[]> bfsQ = new LinkedList<int[]>();
	private static Queue<int[]> checkQ = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// If not check yet
				if (!visited[i][j]) {
					// If mountain peak
					if (check(i, j))
						result++;
				}
			}
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	private static final int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private static boolean check(int i, int j) {
		// Find all neighborhood cell that have same height
		int h = map[i][j];
		visited[i][j] = true;
		bfsQ.add(new int[] { i, j });
		while (!bfsQ.isEmpty()) {
			int[] v = bfsQ.poll();
			checkQ.add(v);
			i = v[0];
			j = v[1];

			// For all neighborhood cell
			for (int k = 0; k < 8; k++) {
				int mi = i + di[k];
				int mj = j + dj[k];

				// Range check
				if (mi < 0 || mj < 0 || mi == N || mj == M)
					continue;

				// Visit check
				if (visited[mi][mj])
					continue;

				// Height check
				if (map[mi][mj] == h) {
					// Set visit and add to queue
					visited[mi][mj] = true;
					bfsQ.add(new int[] { mi, mj });
				}
			}
		}

		// Check it is a mountain peak
		while (!checkQ.isEmpty()) {
			int[] v = checkQ.poll();
			i = v[0];
			j = v[1];

			// For all neighborhood cell
			for (int k = 0; k < 8; k++) {
				int mi = i + di[k];
				int mj = j + dj[k];

				// Range check
				if (mi < 0 || mj < 0 || mi == N || mj == M)
					continue;

				// Height check(If not mountain peak)
				if (map[mi][mj] > h) {
					// Cleaning queue
					checkQ.clear();
					// Return false
					return false;
				}
			}
		}

		return true;
	}

}
