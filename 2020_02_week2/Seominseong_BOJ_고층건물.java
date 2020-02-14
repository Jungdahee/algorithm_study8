package S04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class HighBuilding {

	static BufferedReader br;
	static BufferedWriter bw;
	private static int result;
	private static int N;
	private static int[] arr;
	private static int[] cnt;

	public static void Input() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		cnt = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	/*
	 * 선분의 방정식으로 풀면 틀려서 질문글 확인, CCW 알고리즘 사용
	 * 
	 * CCW 알고리즘 : CounterClockWise, 세 점에 대한 방향성을 구하는 알고리즘
	 * 방향성 : 반시계방향, 시계방향, 평행
	 * 반시계 :       1
	 *          2
	 *                 3
	 *                 
	 * 시계 :     1
	 *                 2
	 *             3
	 *         
	 * 평행 :     1  2  3
	 * 
	 * 좌표 3개가 (x1, y1) (x2, y2) (x3, y3) 가 있고
	 * s = (x2 - x1)(y3 - y1) - (y2 - y1)(x3 -x1) 일 때
	 * s > 0 이면 반시계방향
	 * s < 0 이면 시계방향
	 * s == 0 이면 평행한 것 이다
	 * 
	 * 이 알고리즘을 통해 두 선분이 교차하는지 확인하는 방법
	 * 
	*/
	public static void Processing() {
		// 서로 다른 두 빌딩에 대해 비교
		for (int i = 0; i < N - 1; i++) {
			// i 건물의 좌표
			long ix = i;
			long iy = arr[i];
			for (int j = i + 1; j < N; j++) {
				// j 건물의 좌표
				long jx = j;
				long jy = arr[j];
				
				// i 건물과 j 건물 사이 모든 k 건물에 대해 검사
				boolean check = true;
				for(int k = i + 1; k < j; k++) {
					// k 건물의 좌표
					long kx = k;
					long ky = arr[k];
					
					// 위 세 좌표의 방향이 반시계 방향을 이루는 경우에만
					// i 건물과 j 건물이 서로 볼 수 있음
					long s = (kx - ix) * (jy - iy) - (ky - iy) * (jx - ix);
					if(s <= 0) {
						check = false;
						break;
					}
				}

				// i 와 j 가 서로 볼 수 있을 때
				if (check) {
					cnt[i]++;
					cnt[j]++;
				}
			}
		}

		// 가장 큰 카운트가 정답
		result = 0;
		for (int v : cnt)
			if (result < v)
				result = v;
	}

	public static void Output() throws Exception {
		bw.write(String.valueOf(result));
	}

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Input();
		Processing();
		Output();

		bw.flush();
		bw.close();
		br.close();
	}
}