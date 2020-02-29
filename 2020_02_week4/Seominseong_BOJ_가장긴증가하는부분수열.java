package S08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = 1;
		for (int i = 1; i < N; i++) {
			int n = nums[i];
			int max = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < n) {
					if (dp[j] + 1 > max)
						max = dp[j] + 1;
				}
			}
			dp[i] = max;
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			if (result < dp[i])
				result = dp[i];
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}
