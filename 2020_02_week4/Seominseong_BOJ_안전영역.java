package S07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seominseong_BOJ_안전영역 {

	private static int N;
	private static int max;
	private static int[][] map;
	private static int[][] visited;
	private static int visitedN;

	private static Queue<int[]> bfsQ = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Get size of map
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];
		visited = new int[N + 1][N + 1];
		visitedN = 1;

		max = 1;

		int minH = 101;
		int maxH = 0;

		// Get information of map
		StringTokenizer st;
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				int n = Integer.parseInt(st.nextToken());
				map[r][c] = n;

				// Find minimum and maximum height
				minH = (minH > n) ? n : minH;
				maxH = (maxH < n) ? n : maxH;
			}
		}

		// Check all possible case
		for (int rain = minH; rain < maxH; rain++) {
			int count = 0;
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					// If not visited
					if (visited[r][c] < visitedN) {
						// If safe area
						if (map[r][c] > rain) {
							bfs(r, c, rain);
							count++;
						}
					}
				}
			}
			visitedN++;
			max = (max < count) ? count : max;
		}

		bw.write(String.valueOf(max));

		bw.flush();
		bw.close();
		br.close();
	}

	private static final int[] dr = { 0, 0, -1, 1 };
	private static final int[] dc = { -1, 1, 0, 0 };

	private static void bfs(int r, int c, int rain) {
		visited[r][c] = visitedN;
		bfsQ.add(new int[] { r, c });
		while (!bfsQ.isEmpty()) {
			int[] v = bfsQ.poll();
			r = v[0];
			c = v[1];

			for (int i = 0; i < 4; i++) {
				int mr = r + dr[i];
				int mc = c + dc[i];

				// Check height
				if (map[mr][mc] <= rain)
					continue;

				// Check if already visit
				if (visited[mr][mc] == visitedN)
					continue;

				// Set visited and add to queue
				visited[mr][mc] = visitedN;
				bfsQ.add(new int[] { mr, mc });
			}
		}
	}

}
