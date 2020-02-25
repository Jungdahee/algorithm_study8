package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seominseong_BOJ_벽부수고이동하기 {

	static class Info implements Comparable<Info> {
		int r;
		int c;
		int cnt;
		int num;

		public Info(int r, int c, int cnt, int num) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.num = num;
		}

		@Override
		public int compareTo(Info arg0) {
			int ret = this.cnt - arg0.cnt;
			if (ret == 0)
				ret = arg0.num - this.num;
			return ret;
		}
	}

	private static int C;
	private static int R;

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		String map[] = new String[R];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine();
		}

		int[][][] memory = new int[R][C][2];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				memory[i][j][0] = Integer.MAX_VALUE;
				memory[i][j][1] = Integer.MAX_VALUE;
			}
		}

		Queue<Info> queue = new PriorityQueue<Info>();
		queue.add(new Info(0, 0, 1, 1));
		memory[0][0][1] = 1;
		while (!queue.isEmpty()) {
			Info v = queue.poll();
			int r = v.r;
			int c = v.c;
			int cnt = v.cnt;
			int num = v.num;

			for (int i = 0; i < 4; i++) {
				int mr = r + dr[i];
				int mc = c + dc[i];
				int mnum = num;

				// 범위검사
				if (mr < 0 || mc < 0 || mr >= R || mc >= C)
					continue;

				// 벽검사
				if (map[mr].charAt(mc) == '1') {
					// num 이 충분하면 부수고 그렇지 않다면 continue
					if (mnum == 1)
						mnum--;
					else
						continue;
				}

				// 해당 지역 이미 간적 있는지 메모리 검사
				if (memory[mr][mc][mnum] <= (cnt + 1))
					continue;

				// 큐에 넣기
				memory[mr][mc][mnum] = cnt + 1;
				queue.add(new Info(mr, mc, cnt + 1, mnum));
			}
		}

		// 출력
		int result = Math.min(memory[R - 1][C - 1][0], memory[R - 1][C - 1][1]);
		result = (result == Integer.MAX_VALUE) ? -1 : result;
		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}
