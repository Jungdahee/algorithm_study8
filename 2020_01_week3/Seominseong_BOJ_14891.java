import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class GearObj {
	public char[] state;
	public boolean direction;

	public GearObj(String str) {
		state = new char[8];
		for (int i = 0; i < 8; i++)
			state[i] = str.charAt(i);
	}

	private void rotateClockwise() {
		char t = state[7];
		for (int i = 6; i >= 0; i--)
			state[i + 1] = state[i];
		state[0] = t;
	}

	private void rotateCounterclockwise() {
		char t = state[0];
		for (int i = 1; i <= 7; i++)
			state[i - 1] = state[i];
		state[7] = t;
	}

	public void rotate() {
		// direction 이 true 면 시계방향으로 회전
		if (direction)
			rotateClockwise();
		// direction 이 false 면 반시계방향으로 회전
		else
			rotateCounterclockwise();
	}
}

public class Gear {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 톱니바퀴 상태 입력
		GearObj[] gear = new GearObj[4];
		for (int i = 0; i < 4; i++) {
			gear[i] = new GearObj(br.readLine());
		}

		// 명령 입력
		Queue<GearObj> queue = new LinkedList<GearObj>();
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			// 돌릴 톱니바퀴의 인덱스 받기
			int idx = Integer.parseInt(st.nextToken()) - 1;

			// 톱니바퀴를 돌리는 방향 받기, true 가 시계방향
			boolean dir = (Integer.parseInt(st.nextToken()) == 1) ? true : false;

			// 먼저 돌려야 할 톱니바퀴를 큐에 넣기
			gear[idx].direction = dir;
			queue.add(gear[idx]);

			// 돌릴 톱니바퀴의 왼쪽에 위치한 톱니바퀴들 중 돌려야 할 것 고르기
			boolean sub_dir = !dir;
			for (int j = idx - 1; j >= 0; j--) {
				// j번 기어의 오른쪽이 j + 1 번 기어의 왼쪽가 다를 때 j 번을 돌려야 됨
				if (gear[j].state[2] != gear[j + 1].state[6]) {
					gear[j].direction = sub_dir;
					queue.add(gear[j]);
					sub_dir = !sub_dir;
				}
				// 만약 돌아가지 않는 톱니바퀴가 있으면 그 왼쪽은 절대 안돌아감
				else
					break;
			}

			// 돌릴 톱니바퀴의 오른쪽 ""
			sub_dir = !dir;
			for (int j = idx + 1; j < 4; j++) {
				// j번 기어의 왼쪽이 j - 1 번 기어의 오른쪽과 다를 때 j 번을 돌려야 됨
				if (gear[j].state[6] != gear[j - 1].state[2]) {
					gear[j].direction = sub_dir;
					queue.add(gear[j]);
					sub_dir = !sub_dir;
				}
				// 만약 돌아가지 않는 톱니바퀴가 있으면 그 오른쪽은 절대 안돌아감
				else
					break;
			}

			// 큐에서 돌려야 할 톱니바퀴를 모두 꺼내서 돌리기
			while (!queue.isEmpty())
				queue.poll().rotate();
		}

		// 점수 계산하기
		int add = 1;
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i].state[0] == '1') {
				score += add;
			}
			add <<= 1;
		}

		// 점수 출력하기
		bw.write(score + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
