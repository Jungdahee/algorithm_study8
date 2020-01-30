import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class UginongBetu {

	static public int M, N, K, count;
	static public boolean[][] map;

	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { 1, -1, 0, 0 };

	public static boolean dfs(int x, int y) {
		// 배추가 없는 칸이거나 이미 방문했으면 재귀 안함
		if (!map[x][y])
			return false;

		// 지금 칸 방문표시
		map[x][y] = false;

		// 네 방향 탐색
		int mx, my;
		for (int i = 0; i < 4; i++) {
			mx = x + dx[i];
			my = y + dy[i];

			if (mx >= 0 && mx < M && my >= 0 && my < N)
				dfs(mx, my);
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 테스트케이스의 수 입력
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int x, y;
		for (int t = 0; t < T; t++) {
			// M, N, K 입력
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 지도 만들고 배추 위치 표시
			map = new boolean[M][N];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[x][y] = true;
			}

			// 모든 위치에 대해 dfs 탐색 수행
			count = 0;
			for (x = 0; x < M; x++) {
				for (y = 0; y < N; y++) {
					// dfs 가 true 를 반환하면 이어져 있는 영역 한번 탐색 한 것
					if (dfs(x, y))
						count++;
				}
			}

			// 필요한 지렁이 수 출력
			bw.write(count + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
