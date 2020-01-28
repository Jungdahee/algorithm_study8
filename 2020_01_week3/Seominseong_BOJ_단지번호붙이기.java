import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class DanjiNumbering {

	public static int dfs(boolean[][] map, boolean[][] isVisit, int x, int y, int N) {
		// 집이 아니거나 이미 방문한 칸인 경우 0 반환
		if (!map[x][y] || isVisit[x][y])
			return 0;

		// 집이고, 아직 방문하지 않았으면 방문표시 한 후 값을 1로 줌
		isVisit[x][y] = true;
		int val = 1;

		// 위쪽 탐색 하며 연결된 집들의 수를 val 에 더함
		if (y < N - 1 && map[x][y + 1] && !isVisit[x][y + 1])
			val += dfs(map, isVisit, x, y + 1, N);

		// 아래 탐색 하며 연결된 집들의 수를 val 에 더함
		if (y > 0 && map[x][y - 1] && !isVisit[x][y - 1])
			val += dfs(map, isVisit, x, y - 1, N);

		// 오른쪽 탐색 하며 연결된 집들의 수를 val 에 더함
		if (x < N - 1 && map[x + 1][y] && !isVisit[x + 1][y])
			val += dfs(map, isVisit, x + 1, y, N);

		// 왼쪽 탐색 하며 연결된 집들의 수를 val 에 더함
		if (x > 0 && map[x - 1][y] && !isVisit[x - 1][y])
			val += dfs(map, isVisit, x - 1, y, N);

		// 이 함수를 호출한 좌표로부터 연결된 집의 수 반환
		return val;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 지도 크기 입력
		int N = Integer.parseInt(br.readLine());

		// 지도 입력, 집이 있으면 true, 아니면 false
		String str;
		boolean[][] map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[j][i] = (str.charAt(j) == '1') ? true : false;
			}
		}

		// (0, 0) 부터 모든 칸을 시작으로 dfs 알고리즘을 통해 탐색
		boolean[][] isVisit = new boolean[N][N];
		int val;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				val = dfs(map, isVisit, i, j, N);
				if (val > 0)
					list.add(val);
			}
		}

		// list 정렬시키기
		Collections.sort(list);

		// 단지 수 출력
		int len = list.size();
		bw.write(len + "\n");

		// 단지 내 집 수 출력
		for (int i = 0; i < len; i++)
			bw.write(list.get(i) + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

}
