import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	// 친구간 정보를 연결리스트(그래프) 형태로 표현하기 위한 클래스
	static class Friend {
		public int no;
		public Friend next;

		public Friend() {
			next = null;
		}

		public Friend(int no) {
			this.no = no;
		}

		public void add(Friend f) {
			f.next = next;
			next = f;
		}
	}

	// 입력, 출력, 처리 등에 사용될 전역변수
	static int N, result;
	static Friend[] friends;

	public static void Input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		friends = new Friend[N];

		String str;
		for (int i = 0; i < N; i++) {
			// 객체 배열의 경우 각각 객체를 생성해줘야함
			friends[i] = new Friend();

			str = br.readLine();
			for (int j = 0; j < N; j++) {
				if (str.charAt(j) == 'Y') {
					// i 번의 친구로 j 번 연결하기
					friends[i].add(new Friend(j));
				}
			}
		}

		br.close();
	}

	public static void Processing() {
		// 2-친구 수를 저장할 변수
		int count = 0;
		// 2-친구가 누구인지 저장할 배열
		boolean[] two_f = new boolean[N];
		// 친구 정보를 저장할 변수
		Friend f, f2;

		// i 번 사람의 2-친구 수 계산
		for (int i = 0; i < N; i++) {
			// two_f 초기화
			for (int j = 0; j < N; j++)
				two_f[j] = false;
			// count 초기화
			count = 0;

			// i 번 사람의 모든 친구에 대해
			f = friends[i].next;
			while (f != null) {
				// two_f 리스트에 추가
				two_f[f.no] = true;

				// 친구의 친구들도 two_f 리스트에 추가
				f2 = friends[f.no].next;
				while (f2 != null) {
					// 나 자신은 제외
					if (f2.no != i)
						two_f[f2.no] = true;
					f2 = f2.next;
				}
				f = f.next;
			}

			// 2-친구 수 세기
			for (int j = 0; j < N; j++)
				if (two_f[j])
					count++;
			// 최댓값과 비교하고 갱신
			if (result < count)
				result = count;
		}
	}

	public static void Output() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// result + "" 보다 String.valueOf(result) 가 더 빠르다고 함
		bw.write(String.valueOf(result));

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
