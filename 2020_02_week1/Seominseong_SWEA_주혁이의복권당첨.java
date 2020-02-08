import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lotto {

	static class Pair {
		public int idx;
		public char c;

		public Pair(int idx, char c) {
			this.idx = idx;
			this.c = c;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M, result;
	static String lotto[][], nums[];
	static boolean[] isVisit;
	static Pair[] plist = new Pair[8];

	public static void Input() throws IOException {
		String[] str;

		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		// 당첨 번호와 당첨금 문자열 그대로 받기
		lotto = new String[N][2];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");

			lotto[i][0] = str[0];
			lotto[i][1] = str[1];
		}

		// 복권 번호들 LinkedList 로 받기
		nums = new String[M];
		isVisit = new boolean[M];
		for (int i = 0; i < M; i++)
			nums[i] = br.readLine();
	}

	public static void Processing() {
		String rule;
		int idx;
		result = 0;
		// 모든 당첨 규칙에 대해 반복
		for (int i = 0; i < N; i++) {
			// 당첨 번호 규칙 가져오기
			rule = lotto[i][0];
			// 규칙에서 숫자의 위치와 그 위치의 숫자 쌍 찾아 배열에 넣기
			idx = 0;
			for (int j = 0; j < 8; j++)
				if (rule.charAt(j) != '*')
					plist[idx++] = new Pair(j, rule.charAt(j));
			// 모든 복권 번호와 이 규칙 비교하기
			for (int j = 0; j < M; j++) {
				// 당첨 처리된 번호가 아닐때
				if (!isVisit[j]) {
					// 복권번호 가져오기
					String num = nums[j];
					// 모든 규칙과 비교
					int k;
					for (k = 0; k < idx; k++) {
						Pair p = plist[k];
						// 다른 패턴이 발견되면 탈출
						if (num.charAt(p.idx) != p.c)
							break;
					}
					// 위에서 k 가 idx로 탈출했다면 당첨번호가 나온 것
					if (k == idx) {
						// 당첨 처리하기
						isVisit[j] = true;
						// 결과에 금액 더하기
						result += Integer.parseInt(lotto[i][1]);
					}
				}
			}
		}
	}

	public static void Output(int t) throws IOException {
		bw.write("#" + t + " " + result + "\n");
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			Input();
			Processing();
			Output(t);
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
