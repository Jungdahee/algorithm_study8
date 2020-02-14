package S04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RoomNumber {

	static class Pair implements Comparable<Pair> {
		int no;
		int cost;

		public Pair(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair arg0) {
			if (this.cost == arg0.cost) {
				return arg0.no - this.no;
			}
			return this.cost - arg0.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력
		int N = Integer.parseInt(br.readLine());
		int[] costs = new int[N];
		Pair[] nums = new Pair[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			costs[i] = n;
			nums[i] = new Pair(i, n);
		}

		int money = Integer.parseInt(br.readLine());

		// 정렬
		Arrays.sort(nums);

		// 가장 적은 가격의 숫자 정보 가져오기
		int no = nums[0].no;
		int cost = nums[0].cost;

		// 가장 적은 가격의 숫자를 최대한 사기
		// M : 가장 적은 가격의 숫자를 산 개수
		// A : 가장 적은 가격의 숫자를 최대한 사고 남은 돈
		int M = money / cost;
		int A = money % cost;

		// 가장 작은 가격의 숫자가 0이면 반드시 다른 숫자를 사야함
		boolean check = false;
		if (no == 0) {
			check = true;
		}

		StringBuilder sb = new StringBuilder();
		while (M > 0) {
			// 가장 싼 숫자 하나 팔기
			M--;
			A += cost;

			// 가장 싼 숫자 보다 더 큰 수를 살 수 있는지 확인
			boolean buy = false;
			for (int i = N - 1; i > no; i--) {
				if (costs[i] <= A) {
					buy = true;
					check = false;
					A -= costs[i];
					sb.append(i);
					break;
				}
			}

			// 구입한 숫자가 모두 0이면 위에서 숫자를 못샀어도 계속 진행
			if (check) {
				continue;
			}

			// 위에서 숫자를 못샀으면 나가기
			if (!buy) {
				M++;
				break;
			}
		}

		// 많이 사 놓은 가장 싼 숫자 뒤에 모두 붙이기
		for (int i = 0; i < M; i++) {
			sb.append(no);
		}

		// 만약 아무 숫자도 못샀다면 0 만들기
		if (sb.length() == 0)
			sb.append(0);

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();

	}

}
