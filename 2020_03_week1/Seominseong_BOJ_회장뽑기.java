package S09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_회장뽑기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Get number of person
		int N = Integer.parseInt(br.readLine());

		// Initializing array
		long[] person = new long[N];
		for (int i = 0; i < N; i++)
			person[i] = (1L << i);

		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;

			if (n1 < 0)
				break;

			// Set friend
			person[n1] |= (1L << n2);
			person[n2] |= (1L << n1);
		}

		// Value when everyone is friend
		long all = (1L << N) - 1;

		// Candidate array
		int[] res = new int[N];
		int resIdx = 0;
		// Candidate score
		int score = N;
		// Calculate score
		for (int i = 0; i < N; i++) {
			long friends = person[i];
			// Default score = 1
			int count = 1;
			while (count <= score) {
				// If find all person via friends
				// then count is score
				if (friends == all)
					break;

				// Add friends via current friends
				long newFriends = friends;
				for (int j = 0; j < N; j++) {
					if ((friends & (1L << j)) != 0) {
						newFriends |= person[j];
					}
				}
				friends = newFriends;

				// Increase score
				count++;
			}

			// If same score then add candidate
			if (score == count) {
				res[resIdx++] = (i + 1);
			}
			// If lower score then reset array and add candidate
			else if (score > count) {
				score = count;
				resIdx = 0;
				res[resIdx++] = (i + 1);
			}
		}

		if (resIdx == 0)
			score = 0;

		// Write result
		StringBuilder sb = new StringBuilder();
		sb.append(score).append(" ").append(resIdx).append("\n");

		for (int i = 0; i < resIdx; i++) {
			sb.append(res[i]).append(" ");
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

}
