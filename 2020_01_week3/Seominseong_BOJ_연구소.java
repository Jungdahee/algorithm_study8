import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// x, y 좌표 값을 저장할 클래스
class XY_pair {
	public int x;
	public int y;

	public XY_pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Lab {
	// nCr (n개 중 r개를 선택하는 조합) 을 구하는 함수
	// 출처 : https://www.baeldung.com/java-combinations-algorithm
	public static List<int[]> get_combinations(int n, int r) {
		// 모든 combination 을 담을 리스트
		List<int[]> combinations = new ArrayList<>();
		// 각 combination 은 0 ~ (n - 1) 의 값을 가지는 r 개의 숫자 조합
		int[] combination = new int[r];

		// initialize with lowest lexicographic combination
		// 가장 낮은 숫자 조합의 combination 만들기
		// lexicographic : 사전 식 (구글 번역기)
		for (int i = 0; i < r; i++) {
			combination[i] = i;
		}

		// combination 의 가장 마지막 값이 n 보다 작은 동안 반복
		while (combination[r - 1] < n) {
			// 현재 만들어져 있는 combination 을 리스트에 추가
			combinations.add(combination.clone());

			// generate next combination in lexicographic order
			// 다음 순서의 combination 만들기
			// 먼저 t 변수를 통해 현재 combination 의 마지막 값을 가리키기
			int t = r - 1;
			// t가 0이 아니고 combination[t] 가 n - r + t 와 같다면 t 감소
			// n - r + t 는 combination 의 t 자리에서 가질 수 있는 최대 값
			// 즉, 이미 최대값을 가졌다면 t 를 그 이전을 가리키게 수정하기
			while (t != 0 && combination[t] == n - r + t) {
				t--;
			}
			// 위에서 설정된 t 위치의 combination 값 하나 증가시키기
			// 즉, t 위치의 combination 값이 최대가 될 때 까지 증가하게 됨
			// 이때 위치 t 는 마지막 위치에서부터 하나씩 앞으로 이동
			// 이로인해 생성되는 combinations 는 정렬된 순서를 가지게 생성됨
			combination[t]++;
			// (t + 1 ~ 마지막) combination 값을 이전 위치의 combination 값에 1을 더한 값으로 대체
			// t 위치의 값이 증가되었을 때 t + 1 위치의 값은 t 위치의 값 보다 커야한다(중복을 허용하지 않음)
			// 이러한 (x 위치의 값) < (x + 1 위치의 값) 은 combination 내 모든 위치에서 적용된다.
			for (int i = t + 1; i < r; i++) {
				combination[i] = combination[i - 1] + 1;
			}
		}

		// 만들어진 combination 리스트 반환
		return combinations;
	}

	public static void dfs(int[][] map, boolean[][] isVisit, int x, int y, int M, int N) {
		// 이미 방문했다면 반환하기
		if (isVisit[x][y])
			return;

		// 아직 방문 안했다면 방문 표시 하기
		isVisit[x][y] = true;

		// 윗 칸 확인, 벽이 아닌 경우 바이러스를 확산시키고 그 칸부터 또 확산시키기
		if ((y > 0) && (map[x][y - 1] != 1)) {
			map[x][y - 1] = 2;
			dfs(map, isVisit, x, y - 1, M, N);
		}

		// 아래
		if ((y < N - 1) && (map[x][y + 1] != 1)) {
			map[x][y + 1] = 2;
			dfs(map, isVisit, x, y + 1, M, N);
		}

		// 왼쪽
		if ((x > 0) && (map[x - 1][y] != 1)) {
			map[x - 1][y] = 2;
			dfs(map, isVisit, x - 1, y, M, N);
		}

		// 오른쪽
		if ((x < M - 1) && (map[x + 1][y] != 1)) {
			map[x + 1][y] = 2;
			dfs(map, isVisit, x + 1, y, M, N);
		}
	}

	public static int[][] copyMap(int[][] original, int x, int y) {
		int[][] copy = new int[x][y];
		for (int i = 0; i < x; i++) {
			copy[i] = original[i].clone();
		}
		return copy;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N, M 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 지도 만들기
		ArrayList<XY_pair> zero_list = new ArrayList<>();
		int[][] map = new int[M][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());

				// 빈 공간의 좌표 저장
				if (map[j][i] == 0)
					zero_list.add(new XY_pair(j, i));
			}
		}

		// 빈 공간 중 벽을 세울 수 있는 3개의 모든 조합 구하기
		int zero_len = zero_list.size();
		List<int[]> combinations = get_combinations(zero_len, 3);

		// 각 조합에 대해 벽을 세우고 바이러스를 확산시킨 후 0 의 갯수 세기
		int max = 0;
		for (int[] combination : combinations) {
			// 1. 임시로 벽을 세워보기 위해 복사된 지도 만들기
			// ** 2차원 배열의 경우 copy = arr.clone() 사용시
			// copy 와 arr 은 다른 레퍼런스 값을 가지지만
			// copy[0] 과 arr[0] 은 같은 레퍼런스 값을 가져
			// copy[0][0] = 1 을 하면 arr[0][0] 값도 수정되는 문제가 있어
			// 2차원 배열은 반복문을 통해 복사를 해야한다.
			int[][] map_copy = copyMap(map, M, N);

			// 2. 벽 세우기
			for (int i = 0; i < 3; i++) {
				XY_pair p = zero_list.get(combination[i]);
				map_copy[p.x][p.y] = 1;
			}

			// 3. 바이러스 확산시키기
			boolean[][] isVisit = new boolean[M][N];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					// 바이러스 칸인 경우에만 확산 시작
					if (map_copy[i][j] == 2)
						dfs(map_copy, isVisit, i, j, M, N);
				}
			}

			// 4. 빈칸 세기
			int sum = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (map_copy[j][i] == 0)
						sum++;

			// 5. 빈칸의 크기가 이전보다 크면 최댓값 갱신하기
			if (max < sum)
				max = sum;
		}

		// 안전영역의 최대 크기 쓰기
		bw.write(max + "");

		bw.flush();
		bw.close();
		br.close();
	}

}
