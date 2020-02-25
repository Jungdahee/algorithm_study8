package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_숨바꼭질3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 수빈과 동생의 위치 입력받기
		int subin = Integer.parseInt(st.nextToken());
		int dongseng = Integer.parseInt(st.nextToken());

		// Dynamic Programming - 출처: d2bug
		// 처음에 사용했던 백트래킹 완전탐색방법 : 228 ms
		// 맞은사람의 코드 중 가장 빠른 DP 방법 : 88 ms
		int[] dp = new int[dongseng + 1];

		// 0번 위치에서 동생의 위치까지 수빈이가 가는데 걸리는 시간의 최소값 계산
		for (int i = 0; i <= dongseng; i++) {
			// 0 부터 수빈이의 위치까지 수빈이가 가는데 걸리는 시간 저장
			if (i <= subin) {
				dp[i] = subin - i;
			}
			// 수빈이 앞부터 동생 위치까지 수빈이가 가는데 걸리는 시간 저장
			else {
				// 먼저 이전 칸에서 한 칸 움직이는 경우 걸리는 시간
				int move = dp[i - 1] + 1;
				// 순간이동을 사용해 올 수 있는 경우를 위한 변수
				int teleport = Integer.MAX_VALUE;

				// i 가 짝수일 때 순간이동 하는 경우에 대한 시간 계산
				if (i % 2 == 0) {
					// (i / 2) 번 칸에서 순간이동으로 올 수 있다면 그 시간
					if ((i / 2) > 0)
						teleport = dp[i / 2];
				}
				// i 가 홀수일 때 두 가지 순간이동하는 경우에 대한 시간 계산
				else {
					// 1. (i - 1) 번 칸으로 순간이동 하고 앞으로 한 칸 간 경우
					if (((i - 1) / 2) > 0)
						teleport = dp[(i - 1) / 2] + 1;

					// 2. (i + 1) 번 칸으로 순간이동 하고 뒤로 한 칸 간 경우
					if (((i + 1) / 2) > 0)
						teleport = Math.min(teleport, dp[(i + 1) / 2] + 1);
				}

				// 이전 칸에서 한칸 움직여 오는것과 순간이동을 사용한 것 중
				// 더 적은 시간이 걸린 경우 저장
				dp[i] = Math.min(move, teleport);
			}
		}

		bw.write(String.valueOf(dp[dongseng]));

		bw.flush();
		bw.close();
		br.close();
	}

}
