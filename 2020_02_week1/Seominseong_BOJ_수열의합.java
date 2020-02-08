import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SumOfSequence {

	static int N, L, start = 0, iter;
	static boolean check;

	public static void Input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] strs = br.readLine().split(" ");
		N = Integer.parseInt(strs[0]);
		L = Integer.parseInt(strs[1]);

		br.close();
	}

	public static void Processing() {
		check = false;
		int num, sum;
		for (int i = L; i <= 100; i++) {

			// 이 수열의 가운데에 위치할 숫자 구하기
			num = N / i;

			// 짝수개의 수열
			if (i % 2 == 0) {
				// 1. 이 숫자가 가운데의 두 숫자 중 왼쪽에 위치할 때
				// 이 수열에 음수가 포함되면 답이 없는 문제
				if (num - i / 2 + 1 < 0)
					break;
				// 수열의 합 구하기
				sum = (2 * num + 1) * (i / 2);
				// 합이 N 과 같으면 이 수열의 시작 숫자 설정
				if (sum == N) {
					start = num - i / 2 + 1;
					check = true;
					iter = i;
					break;
				}

				// 2. 이 숫자가 가운데의 두 숫자 중 오른쪽에 위치할 때
				// 이 수열에 음수가 포함되면 답이 없는 문제
				if (num - i / 2 < 0)
					break;
				// 수열의 합 구하기
				sum = (2 * num - 1) * (i / 2);
				// 합이 N 과 같으면 수열의 시작 숫자 설정
				if (sum == N) {
					start = num - i / 2;
					check = true;
					iter = i;
					break;
				}
			}
			// 홀수개의 수열
			else {
				// 이 수열에 음수가 포함되게 된다면 이 문제는 답이 없음
				if (num - i / 2 < 0)
					break;
				// 이 수열의 총 합 구하기
				sum = num + num * 2 * (i / 2);
				// 합이 N 과 같으면 수열의 시작 숫자 설정
				if (sum == N) {
					start = num - i / 2;
					check = true;
					iter = i;
					break;
				}
			}

		}
	}

	public static void Output() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 수열이 존재한다면
		if (check)
			for (int j = 0; j < iter; j++)
				bw.write(start++ + " ");
		// 100개 이하인 수열이 존재하지 않는다면
		else
			bw.write(-1 + "");

		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// 입력
		Input();
		// 처리
		Processing();
		// 출력
		Output();

	}

}
