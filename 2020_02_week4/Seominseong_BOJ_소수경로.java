package S07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seominseong_BOJ_소수경로 {

	private static boolean[] isNotPrime;
	private static int[] step;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		isNotPrime = new boolean[10000];
		step = new int[10000];

		Queue<int[]> queue = new LinkedList<int[]>();

		// Find prime number using sieve of Eratosthenes
		findPrime();

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			String bef = st.nextToken();
			int nBef = Integer.parseInt(bef);
			int aft = Integer.parseInt(st.nextToken());

			// If don't need convert
			if (nBef == aft) {
				bw.write("0\n");
				continue;
			}

			// Initialize step array
			initStep();

			// bef is initial password -> need 0 step
			step[nBef] = 0;
			queue.add(new int[] { nBef, 0 });
			while (!queue.isEmpty()) {
				int[] v = queue.poll();
				int p = v[0];
				int s = v[1];

				// If target password
				if (p == aft)
					continue;

				// Convert password to char[]
				char[] pArr = String.valueOf(p).toCharArray();

				// For all position
				for (int i = 0; i < 4; i++) {
					int start = (i == 0) ? 1 : 0;
					char temp = pArr[i];
					// For all Number
					for (int j = start; j < 10; j++) {
						// Change i position number to j
						char ch = (char) ('0' + j);
						pArr[i] = ch;

						// If prime number
						int n = Integer.parseInt(String.valueOf(pArr));
						if (!isNotPrime[n]) {
							// If not visited(step is lower than before)
							if (step[n] > s + 1) {
								step[n] = s + 1;
								queue.add(new int[] { n, s + 1 });
							}
						}

					}
					pArr[i] = temp;
				}
			}

			String result;
			result = (step[aft] == 10000) ? "Impossible" : String.valueOf(step[aft]);
			bw.write(result);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void initStep() {
		for (int i = 1001; i < 10000; i++) {
			step[i] = 10000;
		}
	}

	private static void findPrime() {
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		// Loop range : 2 ~ root(maximum number)
		for (int i = 2; i <= 100; i++) {
			// If prime number
			if (!isNotPrime[i]) {
				// Multiple of prime number is not prime number
				int j = i * i;
				while (j < 10000) {
					isNotPrime[j] = true;
					j += i;
				}
			}
		}
	}

}
