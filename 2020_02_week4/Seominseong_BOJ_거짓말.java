package S06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_거짓말 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Input number of peoples and number of parties
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// Check who is know truth
		st = new StringTokenizer(br.readLine(), " ");
		boolean[] people = new boolean[N + 1];
		int len = Integer.parseInt(st.nextToken());
		for (int i = 0; i < len; i++) {
			people[Integer.parseInt(st.nextToken())] = true;
		}

		// Input information of parties
		boolean[] cantLie = new boolean[M];
		int[][] partyPeople = new int[M][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			len = Integer.parseInt(st.nextToken());

			for (int j = 0, idx = 0; j < len; j++) {
				int n = Integer.parseInt(st.nextToken());

				// If someone at this party knows the truth
				// then Jimin can not lie
				if (people[n]) {
					cantLie[i] = true;
				} else {
					partyPeople[i][idx++] = n;
				}
			}
		}

		// For all party that Jimin can not lie
		for (int i = 0; i < M; i++) {
			if (cantLie[i]) {
				int j = 0;
				int n;
				// Jimin can not lie in front of these people either
				while ((n = partyPeople[i][j++]) > 0) {
					people[n] = true;
				}
			}
		}

		// For all party
		boolean check = true;
		while (check) {
			check = false;
			for (int i = 0; i < M; i++) {
				// If 'i' party not contains people who knows the truth
				if (!cantLie[i]) {
					int j = 0;
					int n;
					// If 'i' party contains people
					// who enjoy party with person who knows the truth
					// then Jimin can not lie this party
					while ((n = partyPeople[i][j++]) > 0) {
						if (people[n]) {
							cantLie[i] = true;
							check = true;
							j = 0;
							while ((n = partyPeople[i][j++]) > 0)
								people[n] = true;
							break;
						}
					}
				}
			}
		}

		// Count the number of parties that Jimin can lie
		int result = 0;
		for (int i = 0; i < M; i++) {
			if (!cantLie[i])
				result++;
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}
