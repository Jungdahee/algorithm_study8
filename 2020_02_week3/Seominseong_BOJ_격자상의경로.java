package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_격자상의경로 {

	private static int R;
	private static int C;
	private static int K;
	private static int result;
	private static int[][] memory;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// R행 C 열의 맵에서 가능한 모든 경로의 수 미리 계산
		memory = new int[16][16];
		for (int i = 2; i <= 15; i++) {
			memory[1][i] = 1;
			memory[i][1] = 1;
		}

		for (int i = 2; i <= 15; i++) {
			for (int j = 2; j <= 15; j++) {
				memory[i][j] = memory[i - 1][j] + memory[i][j - 1];
			}
		}

		result = 0;
		// 꼭 거쳐가야 할 좌표가 있을 때 그 좌표 값 찾기
		if (K > 0) {
			// 그 좌표 값 찾기
			int c = ((K - 1) % C) + 1;
			int r = ((K - 1) / C) + 1;

			// 그 좌표까지 가는데 가능한 경로의 수 계산
			int path1 = memory[r][c];

			// 그 좌표부터 도착지 까지 가는데 가능한 경로의 수 계산
			int path2 = memory[R - r + 1][C - c + 1];

			// 총 경로의 수는 두 경로의 수의 곱
			result = path1 * path2;
		}
		// 꼭 거쳐가야 할 좌표가 없을 때
		else {
			// 출발지부터 도착지까지 가는 데 필요한 경로의 수
			result += memory[R][C];
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

}