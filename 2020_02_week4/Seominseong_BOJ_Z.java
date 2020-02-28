package S07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_Z {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Get N, r, c
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// Calculate size of array
		int iter = N;
		N = 1;
		for (int i = 0; i < iter; i++) {
			N *= 2;
		}

		// Calculate result
		int result = 0;
		// Divide into 4 location and calculate recursively
		while ((N /= 2) > 0) {
			// Find location when divided into 4 location(0, 1, 2, 3)
			int v = 0;
			if (r >= N) {
				r -= N;
				v += 2;
			}
			if (c >= N) {
				c -= N;
				v += 1;
			}

			// Find left-top value on this location
			result += (N * N * v);
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}
