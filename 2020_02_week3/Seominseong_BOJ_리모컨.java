package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seominseong_BOJ_리모컨 {

	private static String strN;
	private static int N;
	private static int M;
	private static boolean[] disable = new boolean[10];
	private static int current;
	private static int result;
	private static char[] buttons;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		strN = br.readLine();
		N = Integer.parseInt(strN);
		M = Integer.parseInt(br.readLine());

		buttons = new char[strN.length()];

		StringTokenizer st = null;
		if (M > 0) {
			st = new StringTokenizer(br.readLine(), " ");
		}
		for (int i = 0; i < M; i++) {
			disable[Integer.parseInt(st.nextToken())] = true;
		}

		current = 100;
		result = Integer.MAX_VALUE;

		// +, - 버튼만 눌렀을 경우
		int num = N - current;
		num = (num < 0) ? -num : num;
		result = (result < num) ? result : num;

		// 해당 채널로 바로 눌러서 갈 수 있는지 확인
		boolean check = true;
		for (int i = 0; i < strN.length(); i++) {
			int n = strN.charAt(i) - '0';
			// 고장난 버튼이 하나라도 있으면 바로 못감
			if (disable[n]) {
				check = false;
				break;
			}
		}
		// 해당 채널로 바로 버튼을 눌러서 갈 수 있다면
		if (check) {
			num = strN.length();
			result = (result < num) ? result : num;
		}
		// 바로 못누른다면 여러 방법으로 눌러봐야 함(모두 고장나지 않았다면)
		else if (M < 10) {
			// 1. 채널 번호보다 더 큰 자리수로 눌러보기
			// 이때 1을 누를 수 있어야 함
			if (!disable[1]) {
				// 1 다음에 들어갈 숫자 중 가장 작은 수 찾기
				int i;
				for (i = 0; i < 10; i++) {
					if (!disable[i])
						break;
				}
				// StringBuilder 로 숫자 만들기
				StringBuilder sb = new StringBuilder();
				sb.append(1);
				for (int j = 0; j < strN.length(); j++) {
					sb.append(i);
				}
				// 숫자로 만들기
				num = Integer.parseInt(sb.toString());
				// result 갱신
				num = num - N + strN.length() + 1;
				result = (result < num) ? result : num;
			}

			// 2. 채널 번호보다 더 작은 자리수로 눌러보기
			// 채널 번호가 2자리수 이상이라면
			if (strN.length() > 1) {
				// 누를 수 있는 가장 큰 수 찾기
				int i;
				for (i = 9; i >= 0; i--) {
					if (!disable[i])
						break;
				}
				// 누르기
				StringBuilder sb = new StringBuilder();
				for (int j = 1; j < strN.length(); j++) {
					sb.append(i);
				}
				// 숫자로 만들기
				num = Integer.parseInt(sb.toString());
				// result 갱신
				num = N - num + strN.length() - 1;
				result = (result < num) ? result : num;
			}
			// 3. 채널 번호와 같은 자리수로 눌러보기
			pressButton(0);
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	private static void pressButton(int i) {
		// 모두 눌렀다면
		if (i == strN.length()) {
			// 숫자로 만들기
			int num = Integer.parseInt(String.valueOf(buttons));
			// + - 버튼 누르는 횟수 계산
			num = N - num;
			num = (num < 0) ? -num : num;
			// 숫자 버튼 누르는 횟수 계산
			num += strN.length();
			// result 갱신
			result = (result < num) ? result : num;

			return;
		}

		// 같은 자리수로 가능한 모든 경우로 눌러보기
		for (int j = 0; j < 10; j++) {
			// 첫 번째 자리에서 0을 누르는 경우는 제외
			if (strN.length() != 1 && i == 0 && j == 0)
				continue;

			if (!disable[j]) {
				buttons[i] = (char) ((int) '0' + j);
				pressButton(i + 1);
			}
		}
	}

}