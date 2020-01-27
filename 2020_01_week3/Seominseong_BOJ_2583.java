import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FindArea {

	public static int dfs(boolean[][] map, boolean[][] isVisit, int x, int y, int M, int N) {
		// (x, y) 가 빈 영역이 아니거나 이미 방문했다면 0 반환
		if (map[x][y] || isVisit[x][y])
			return 0;

		// (x, y) 가 빈 영역이면 기본 넓이르 1로 주고 isVisit 설정
		int val = 1;
		isVisit[x][y] = true;

		// 각 인접 칸에 대해 탐색하며 넓이의 합 구하기
		// 1. 상
		if (y < M - 1)
			val += dfs(map, isVisit, x, y + 1, M, N);

		// 2. 하
		if (y > 0)
			val += dfs(map, isVisit, x, y - 1, M, N);

		// 3. 좌
		if (x > 0)
			val += dfs(map, isVisit, x - 1, y, M, N);

		// 4. 우
		if (x < N - 1)
			val += dfs(map, isVisit, x + 1, y, M, N);

		// 구한 넓이의 합 반환
		return val;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, M, K 값 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// false 값을 가진 칸이 빈칸
		boolean[][] map = new boolean[N][M];

		// 직사각형 좌표 입력받아 map 에 적용
		int x1, x2, y1, y2;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			for (int j = x1; j < x2; j++)
				for (int k = y1; k < y2; k++)
					map[j][k] = true;
		}

		// 직사각형의 모든 칸을 시작점으로 DFS 탐색 수행
		int val;
		boolean[][] isVisit = new boolean[N][M];
		ArrayList<Integer> list = new ArrayList<>();
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				// (x, y) 를 시작점으로 탐색
				val = dfs(map, isVisit, x, y, M, N);
				// 빈 영역이라면 그 넓이를 리스트에 추가
				if (val > 0)
					list.add(val);
			}
		}

		// 영역의 넓이 리스트 정렬
		Collections.sort(list);

		// 영역의 갯수 출력
		int len = list.size();
		bw.write(len + "\n");

		// 영역의 넓이들 출력
		for (int i = 0; i < len; i++) {
			bw.write(list.get(i) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
