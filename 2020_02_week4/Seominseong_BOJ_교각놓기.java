package S06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seominseong_BOJ_교각놓기 {

	static class Bridge implements Comparable<Bridge> {
		int x1;
		int x2;
		int y;

		public Bridge(int x1, int x2, int y) {
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}

		@Override
		public int compareTo(Bridge arg0) {
			// TODO Auto-generated method stub
			return this.y - arg0.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Bridge[] bridge = new Bridge[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());

			bridge[i] = new Bridge(x1, x2, y);
		}

		Arrays.sort(bridge);

		int[] maxY = new int[20001];

		int result = 0;
		for (int i = 0; i < N; i++) {
			int x1 = bridge[i].x1 * 2;
			int x2 = bridge[i].x2 * 2;
			int y = bridge[i].y;

			result += (y - maxY[x1 + 1]);
			result += (y - maxY[x2 - 1]);

			for (int j = x1 + 1; j <= x2 - 1; j++)
				maxY[j] = y;
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}
