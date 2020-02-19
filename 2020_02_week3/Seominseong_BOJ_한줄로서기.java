package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_한줄로서기 {

	private static int N;
	private static int[] arr;
	private static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		result = new int[N];

		dfs(0);

		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(result[i] + " "));
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean dfs(int i) {
		// 모든 사람의 위치가 선택되었을 때
		if (i == N) {
			// 해당 배치가 올바른지 선택
			for (int j = 0; j < N; j++) {
				// j번 위치에 있는 사람의 arr 배열 인덱스
				int n = result[j] - 1;
				// j번 위치에 있는 사람보다 왼쪽에 있는 사람 수
				int cnt = arr[n];
				// j 번 위치부터 0번 위치까지 확인하며 자신보다 큰 사람이 cnt와 같은지 확인
				n++;
				for (int k = j - 1; k >= 0; k--) {
					if (n < result[k])
						cnt--;
				}
				// cnt 가 0이 아니면 올바르지 않은 위치
				if (cnt != 0)
					return false;
			}
			return true;
		}

		// i 번째 사람이 있을 수 있는 위치 선택
		for (int j = arr[i]; j < arr[i] + i + 1; j++) {
			// 이미 누가 있는 자리면 안됨
			if (result[j] != 0)
				continue;
			// 그 위치를 선택
			result[j] = i + 1;
			if (dfs(i + 1))
				return true;
			// 선택 취소
			result[j] = 0;
		}
		return false;
	}

}