import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class WaterBottle {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, K 입력
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);

		// 계산
		Queue<Integer> queue = new LinkedList<>();
		int n = N;
		int m;
		int val = 1;
		while (n > 0) {
			// N 개의 물병을 두개씩 합쳤을 때 남는 물병의 물 양을 리스트에 저장
			m = n % 2;
			if (m == 1)
				queue.add(val);

			// N 개의 물병을 최대한 합쳤으므로 2로 나눔
			n /= 2;
			// 현재 합쳐진 물병은 이전의 2배가 들어있음
			val *= 2;
		}

		// 최대한 합쳤을 때 물병의 수
		int len = queue.size();

		// 물병을 사야한다면 그 갯수 구하기
		int number_of_bottle = 0;
		if (len > K) {
			// 줄여야 할 물병 수
			len = len - K;

			// 큐에 모든 물병이 떨어질 때 까지 반복
			// 이 반복문이 모두 도는 경우: len 이 2 이상이고 K 가 1 일 때
			// 큐에 넣지 않고 가지고 있을 가장 적은 물을 가진 물병의 물 양
			int bottle = queue.poll();
			while (!queue.isEmpty()) {
				// 큐에서 가장 앞에 있는 물의 양 보기
				int v = queue.poll();
				// v - bottle 개의 물을 사야 합쳐서 2 * v 의 물병을 만들 수 있음
				number_of_bottle += (v - bottle);
				// 물병을 합쳤으니 len 을 줄이고, len 이 0이 되면 나가기
				if (--len == 0)
					break;
				// 합쳤으니 이제 가장 적은 물의 양이 v 의 2배가 됨
				bottle = 2 * v;
			}

		}

		// 사야 할 물병 수 출력
		bw.write(number_of_bottle + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
