package S09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Seominseong_BOJ_NQueen {

	private static int[] queens;
	private static int N;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		queens = new int[N];

		result = 0;
		for (int j = 0; j < N; j++) {
			// Put queen in position (0, j)
			dfs(0, j);
		}
		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int i, int j) {
		// If N queen is in board
		if (i == N - 1) {
			result++;
			return;
		}

		// Remember j position of queen
		queens[i++] = j;

		// Check if can put queen there
		int check = 0;
		for (int k = 0; k < i; k++) {
			int qi = k;
			int qj = queens[k];

			int no1 = qj + i - qi;
			int no2 = qj - i + qi;

			check |= (1 << qj);
			check |= (1 << no1);
			check |= (1 << no2);
		}

		// Put next queen (next row)
		for (int j2 = 0; j2 < N; j2++) {
			// If can put queen there
			if ((check & (1 << j2)) == 0) {
				dfs(i, j2);
			}
		}
	}

}
