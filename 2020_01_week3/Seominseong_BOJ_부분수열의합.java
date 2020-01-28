import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SumOfSubsequences {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, S 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		// 수열 입력
		st = new StringTokenizer(br.readLine(), " ");
		int[] sequence = new int[N];
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}

		// 모든 부분수열의 합을 계산해 S와 비교
		int iter = (1 << N) - 1;
		int count = 0;
		for (int i = 1; i <= iter; i++) {
			int i_copy = i;
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if ((i_copy & 1) == 1) {
					sum += sequence[j];
				}
				i_copy >>= 1;
			}
			if (sum == S)
				count++;
		}

		bw.write(count + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
