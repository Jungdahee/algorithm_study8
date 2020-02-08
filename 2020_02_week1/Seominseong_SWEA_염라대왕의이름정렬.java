import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class NameSort {

	static class Name implements Comparable<Name> {
		String name;
		int len;

		// 이름 넣으면서 길이재기
		public void setName(String name) {
			this.name = name;
			this.len = name.length();
		}

		// 비교함수(정렬용)
		@Override
		public int compareTo(Name o) {
			// 길이가 같으면 이름을 사전순으로 정렬
			if (this.len == o.len) {
				return this.name.compareTo(o.name);
			}
			// 이름의 길이 순으로 정렬
			else {
				return this.len - o.len;
			}
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;

	static int N, result;
	static Name[] names = new Name[20000];

	public static void Input() throws IOException {
		// 이름 개수
		N = Integer.parseInt(br.readLine());

		// 이름 받기
		for (int i = 0; i < N; i++) {
			names[i].setName(br.readLine());
		}
	}

	public static void Processing() {
		// 이름 정렬
		Arrays.sort(names, 0, N);
	}

	public static void Output(int t) throws IOException {
		bw.write("#" + t + "\n");
		// 이름들 출력(중복 제거)
		String before = "";
		String current;
		for (int i = 0; i < N; i++) {
			current = names[i].name;
			// 이전에 출력한 이름과 다르다면 출력
			if (!before.equals(current)) {
				bw.write(current + "\n");
				before = current;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 객체 미리 생성
		for (int i = 0; i < 20000; i++)
			names[i] = new Name();

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