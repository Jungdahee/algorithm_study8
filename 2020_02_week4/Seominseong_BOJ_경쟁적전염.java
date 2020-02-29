package S08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_경쟁적전염 {

	private static int[][] map;
	private static int[][][] virus;
	private static int[][] idx;
	private static int[][] temp;

	private static final int[] di = { -1, 1, 0, 0 };
	private static final int[] dj = { 0, 0, -1, 1 };

	private static void push(int n, int i, int j) {
		int pushIdx = idx[n][0]++;
		virus[n][0][pushIdx] = i;
		virus[n][1][pushIdx] = j;
	}

	private static int[] pull(int n) {
		int pullIdx = idx[n][1]++;
		int i = virus[n][0][pullIdx];
		int j = virus[n][1][pullIdx];
		return new int[] { i, j };
	}

	private static boolean isEmpty(int n) {
		return idx[n][0] == idx[n][1];
	}

	private static void change(int n, int pushIdx) {
		int[][] arr = temp;
		temp = virus[n];
		virus[n] = arr;
		idx[n][0] = pushIdx;
		idx[n][1] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virus = new int[K + 1][2][500];
		temp = new int[2][500];
		idx = new int[K + 1][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;

				if (n > 0) {
					push(n, i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int S = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;

		if (map[x][y] != 0) {
			bw.write(String.valueOf(map[x][y]));
			bw.flush();
			bw.close();
			br.close();
			return;
		}

		L: for (int s = 0; s < S; s++) {
			// For all virus
			for (int k = 1; k <= K; k++) {

				// Spread virus
				int pushIdx = 0;
				while (!isEmpty(k)) {
					int[] v = pull(k);
					int i = v[0];
					int j = v[1];

					for (int d = 0; d < 4; d++) {
						int mi = i + di[d];
						int mj = j + dj[d];

						// Range check
						if (mi < 0 || mj < 0 || mi == N || mj == N)
							continue;

						// Virus check
						if (map[mi][mj] == 0) {
							map[mi][mj] = k;
							temp[0][pushIdx] = mi;
							temp[1][pushIdx] = mj;
							pushIdx++;

							if (mi == x && mj == y)
								break L;
						}
					}
				}
				change(k, pushIdx);
			}
		}

		bw.write(String.valueOf(map[x][y]));

		bw.flush();
		bw.close();
		br.close();
	}

}
