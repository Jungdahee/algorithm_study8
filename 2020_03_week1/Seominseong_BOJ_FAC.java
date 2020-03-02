package S09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_FAC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			// Calculate distance
			long dist = Long.parseLong(st.nextToken()) - Long.parseLong(st.nextToken());
			dist *= -1;

			long s = 0;
			long e = dist;
			int d = 1;

			// Greedy
			while (s < e) {
				s += d;
				e -= d;
				d += 1;
			}
			d--;

			// Start point must be lower than end point
			if (s > e) {
				s -= d;
				e += d;
				d--;
			}

			// New distance
			dist = e - s;

			int plus = 0;

			// If distance is 0 (arrived)
			if (dist == 0) {
				plus = 0;
			}
			// Need more calculate
			else if (dist < (d - 1)) {
				// Loop count
				int cnt = 1;
				while (true) {
					dist += (d * 2);
					d--;

					int n = d - 1;
					int mid = n - cnt;
					int sum;
					// Even
					if (cnt % 2 == 0) {
						sum = (n + mid + 1) * cnt + mid;
					}
					// Odd
					else {
						sum = (n + mid) * (cnt + 1) - mid;
					}

					if (sum <= dist)
						break;

					cnt++;
				}

				plus = cnt * 2 + 1;
			}
			// Can arrive by 2 more teleport
			else if (dist > (d + 1)) {
				plus = 2;
			}
			// Can arrive by 1 more teleport
			else {
				plus = 1;
			}

			sb.append(2 * d + plus).append("\n");
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

}
