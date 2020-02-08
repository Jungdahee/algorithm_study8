import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class ThreeThreeTripleGame {

	static class Card {
		public char num;
		public char color;
	}

	static BufferedReader br;
	static BufferedWriter bw;

	static String result;

	static final int B = 0, G = 1, R = 2;
	static char[] numCheck = new char[9];
	static char[] nums = new char[9];
	static boolean[] numVisit = new boolean[9];
	static boolean win;

	static Card[][] cards = new Card[3][9];
	static int[] indexes = new int[3];

	public static void Input() throws IOException {
		String numbers = br.readLine();
		String colors = br.readLine();

		// indexes 배열 초기화
		for (int i = 0; i < 3; i++)
			indexes[i] = 0;

		for (int i = 0; i < 9; i++) {
			char num = numbers.charAt(i);
			char color = colors.charAt(i);

			int idx1 = 0, idx2 = 0;
			switch (color) {
			case 'B':
				idx1 = B;
				idx2 = indexes[B];
				indexes[B]++;
				break;
			case 'G':
				idx1 = G;
				idx2 = indexes[G];
				indexes[G]++;
				break;
			case 'R':
				idx1 = R;
				idx2 = indexes[R];
				indexes[R]++;
			}

			cards[idx1][idx2].num = num;
			cards[idx1][idx2].color = color;
		}
	}

	public static boolean isWin(char[] nums, int idx) {
		// 모두 같은 숫자인지 확인
		boolean same = (nums[idx] == nums[idx + 1] && nums[idx + 1] == nums[idx + 2]);
		// 연속된 숫자인지 확인
		boolean c = (nums[idx] + 1 == nums[idx + 1] && nums[idx + 1] + 1 == nums[idx + 2]);
		// 둘 중 하나라도 맞으면 true 반환
		return same || c;
	}

	public static void permutation(int i, char[] cards, int idx) {
		// 재귀호출을 끝내는 시점
		if (i == idx) {
			if (idx == 3) {
				if (isWin(nums, 0))
					win = true;
			} else if (idx == 6) {
				if (isWin(nums, 0) && isWin(nums, 3))
					win = true;
			} else if (idx == 9) {
				if (isWin(nums, 0) && isWin(nums, 3) && isWin(nums, 6))
					win = true;
			}
			return;
		}

		// 모든 순열 확인
		for (int j = 0; j < idx; j++) {
			if (numVisit[j])
				continue;

			nums[i] = cards[j];
			numVisit[j] = true;
			permutation(i + 1, cards, idx);
			numVisit[j] = false;

			if (win)
				return;
		}
	}

	public static boolean check(char[] cards, int idx) {
		// 검사할 카드갯수가 0이면 나가기
		if (idx == 0)
			return true;
		
		// 모든 순열에 대해 확인하기
		win = false;
		permutation(0, cards, idx);

		return win;
	}

	public static void Processing() {
		// B, G, R 카드 개수 중 하나라도 0 또는 3의 배수가 아니면 정답이 아님
		for (int i = 0; i < 3; i++) {
			if (indexes[i] % 3 != 0) {
				result = "Continue";
				return;
			}
		}

		// 숫자조합이 승리 조건을 만족하는지 보기
		for (int i = 0; i < 3; i++) {
			int idx = indexes[i];
			// 카드들에 대한 숫자 리스트 만들기, numVisit 초기화
			for (int j = 0; j < idx; j++) {
				numCheck[j] = cards[i][j].num;
				numVisit[j] = false;
			}

			// 숫자 리스트 정렬
			Arrays.sort(numCheck, 0, idx);

			// 숫자 조합이 조건에 어긋나면 못이김
			if (!check(numCheck, idx)) {
				result = "Continue";
				return;
			}
		}

		// 여기까지 왔으면 이긴거
		result = "Win";
	}

	public static void Output(int t) throws IOException {
		bw.write("#" + t + " " + result + "\n");
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 미리 객체 리스트 만들어두기
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				cards[i][j] = new Card();

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