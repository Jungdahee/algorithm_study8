package S02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lamp {

	static int N, M, K, max;
	static String[] lamps;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, M 입력
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		// lamp 정보 입력
		lamps = new String[N];
		for (int i = 0; i < N; i++) {
			lamps[i] = br.readLine();
		}

		// K 입력
		K = Integer.parseInt(br.readLine());

		// 모든 행에 대해 반복
		int max = 0;
		for (int i = 0; i < N; i++) {
			// 1. 꺼져있는 램프 수 세기
			int off = 0;
			for (int j = 0; j < M; j++) {
				if (lamps[i].charAt(j) == '0')
					off++;
			}

			// 2. K 번의 스위치 조작으로 그 행을 켤 수 있는지 확인
			boolean check = false;
			// K 가 off 보다 크거나 같아야 모두 켤 수 있다
			if (off <= K) {
				// K - off 의 결과가 짝수 일 경우 모두 켤 수 있음
				if ((K - off) % 2 == 0)
					check = true;
			}

			// 3. 램프를 모두 켤 수 있을 때
			int count = 0;
			if (check) {
				// 그 행과 같은 값을 가지는 행의 수 세기
				for (int j = 0; j < N; j++) {
					if (lamps[i].equals(lamps[j]))
						count++;
				}
			}

			// 4. 가장 많이 켤 수 있는 행의 수 갱신
			if (max < count)
				max = count;
		}

		// 결과 쓰기
		bw.write(max + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
