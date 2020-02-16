package BOJ;

import java.io.*;

public class BOJ_1010_다리놓기 {

	static int n, m, d[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		while(tc-- != 0) {
			String input[] = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			
			d = new int[n + 1][m + 1];
			for(int i = 0; i <= m; i++) d[1][i] = i;
				
			for(int i = 2; i <= n; i++) {
				for(int j = i; j <= m; j++)
					for(int k = j;k >= i; k--)
						d[i][j] += d[i - 1][k - 1];
			}

			System.out.println(d[n][m]);
		}
	}
}
