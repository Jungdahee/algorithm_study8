import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class LeaveJob {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N 입력
		int N = Integer.parseInt(br.readLine());

		// T, P 입력
		int[] T = new int[N];
		int[] P = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());

			// 만약 상담을 할 수 없는 날이 있다면 T, P 모두 0으로 입력
			if (T[i] + i > N) {
				T[i] = 0;
				P[i] = 0;
			}
		}

		// 모든 경우의 수 확인
		int max = 0;
		int iter = (1 << N) - 1;
		for (int i = 1; i <= iter; i++) {
			int i_copy = i;
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if ((i_copy & 1) == 1) {
					sum += P[j];

					// 상담 기간에 따라 상담 못하는 날이 생길 수 있다.
					// 그런 경우를 제외하기 위한 반복문 건너뛰기 코드
					int t = T[j] - 1;
					j += t;
					for (int k = 0; k < t; k++)
						i_copy = i_copy >> 1;
				}
				i_copy = i_copy >> 1;

				if (max < sum)
					max = sum;
			}
		}

		bw.write(max + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
