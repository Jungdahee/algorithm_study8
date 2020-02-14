package S04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bridge {

	static int N, M;

	static int memory[][] = new int[31][31];

	public static void makeMemory() {
		// 첫 번째 행 입력
		for (int i = 1; i < 31; i++) {
			memory[1][i] = i;
		}

		// n 번째 행은 n - 1 번째 행의 값에 따라 계산 됨
		// (i, j) 의 값 = i - 1 행의 첫 번째 값부터 j - 1 번째 값의 합
		// => (i, j - 1) 의 값 + (i - 1, j - 1) 의 값
		for (int i = 2; i < 31; i++) {
			memory[i][i] = 1;
			for (int j = i + 1; j < 31; j++) {
				memory[i][j] = memory[i][j - 1] + memory[i - 1][j - 1];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 모든 테스트케이스에서 사용할 수 있게 모든 경우를 미리 계산해놓기
		makeMemory();

		// 입력
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 입력된 값에 따라 미리 계산해 놓은 값 그대로 출력
			bw.write(memory[N][M] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
