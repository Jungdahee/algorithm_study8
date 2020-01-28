import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class XY {
	public int x;
	public int y;

	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class MazeExploration {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, M 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 미로 정보 입력
		boolean[][] maze = new boolean[M][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[j][i] = (line.charAt(j) == '1') ? true : false;
			}
		}

		// 각 칸을 방문 했는지 확인하는 변수
		boolean[][] isVisit = new boolean[M][N];

		// 각 칸 까지 가는데 지나야 하는 칸 수를 저장할 배열
		int[][] numberOfCell = new int[M][N];

		// BFS 알고리즘을 통한 모든 칸 탐색
		// 1. 큐를 만들고, 탐색을 시작할 좌표를 큐에 넣기
		Queue<XY> queue = new LinkedList<XY>();
		queue.add(new XY(0, 0));
		isVisit[0][0] = true;
		numberOfCell[0][0] = 1;
		// 2. 큐에 원소가 바닥날 때 까지 반복
		while (!queue.isEmpty()) {
			// 3. 큐에서 원소 꺼내고 좌표값 얻기
			XY xy = queue.poll();
			int x = xy.x;
			int y = xy.y;
			int count = numberOfCell[x][y];

			// 4. 큐에서 꺼낸 좌표에 인접한 칸의 좌표들 찾아 큐에 넣기
			// 4-1 상
			if (y > 0 && maze[x][y - 1] && !isVisit[x][y - 1]) {
				queue.add(new XY(x, y - 1));
				isVisit[x][y - 1] = true;
				numberOfCell[x][y - 1] = count + 1;
			}

			// 4-2 하
			if (y < N - 1 && maze[x][y + 1] && !isVisit[x][y + 1]) {
				queue.add(new XY(x, y + 1));
				isVisit[x][y + 1] = true;
				numberOfCell[x][y + 1] = count + 1;
			}

			// 4-3 좌
			if (x > 0 && maze[x - 1][y] && !isVisit[x - 1][y]) {
				queue.add(new XY(x - 1, y));
				isVisit[x - 1][y] = true;
				numberOfCell[x - 1][y] = count + 1;
			}

			// 4-4 우
			if (x < M - 1 && maze[x + 1][y] && !isVisit[x + 1][y]) {
				queue.add(new XY(x + 1, y));
				isVisit[x + 1][y] = true;
				numberOfCell[x + 1][y] = count + 1;
			}
		}

		// 결과 출력
		bw.write(numberOfCell[M - 1][N - 1] + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
