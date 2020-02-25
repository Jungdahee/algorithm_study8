package S06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seominseong_BOJ_암호만들기 {

	static char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
	private static int L;
	private static int C;
	private static char[] arr;
	private static char[] passWord;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// Get L, C
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// Get alphabets
		arr = new char[C];
		String str = br.readLine();
		for (int i = 0; i < C; i++) {
			arr[i] = str.charAt(i << 1);
		}

		// Sort alphabets to lexicographically
		Arrays.sort(arr);

		passWord = new char[L];
		comb(0, 0);

		bw.flush();
		bw.close();
		br.close();
	}

	static void comb(int i, int start) throws IOException {
		if (i == L) {
			// Check the number of vowels
			int cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (Arrays.binarySearch(passWord, vowels[j]) >= 0)
					cnt++;
			}
			if (cnt == 0)
				return;

			// Check the number of consonants
			cnt = L - cnt;
			if (cnt < 2)
				return;

			// Print password
			bw.write(String.valueOf(passWord));
			bw.newLine();

			return;
		}

		int iter = C - L + i;
		for (int j = start; j <= iter; j++) {
			passWord[i] = arr[j];
			comb(i + 1, j + 1);
		}
	}

}
