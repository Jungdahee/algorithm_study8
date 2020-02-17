package BOJ;

import java.io.*;

public class BOJ_1027_고층건물 {

	static int result, height[];
	static double maxIn;
	static boolean check[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		height = new int[n + 1];
		check = new boolean[n + 1][n + 1];
		
		String input[] = br.readLine().split(" ");
		for(int i = 0; i < n; i++) height[i] = Integer.parseInt(input[i]);
		
		for(int i = 0; i < n; i++) {
			int cnt = 0;
			maxIn = Double.NEGATIVE_INFINITY;
			for(int j = i + 1; j < n; j++) {
				double tmpIn = 1.0 * (height[j] - height[i]) / (j - i);
				if(tmpIn > maxIn) {
					maxIn = tmpIn;
					cnt++;
					check[i][j] = true;
				}
			}
			
			for(int j = 0; j < i; j++) if(check[j][i]) cnt++;
			
			if(result < cnt) result = cnt;
		}
		System.out.println(result);
	}
}
