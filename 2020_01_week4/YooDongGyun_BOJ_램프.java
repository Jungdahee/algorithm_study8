package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YooDongGyun_BOJ_1034_램프 {
	static int M;
	static int N;
	static int K;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		arr = new int[N][M];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] str2 = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str2[j]);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		// 입력 끝

		// 행 탐색
		// 행 비교할 때 어떤 열에 대한 원소가 서로 다를 시 열단위 불켜고끄므로 절대 같이 켜질수 없다.
		// 첫고로 같은 것들 중 최대를 찾는다.
		boolean Pass;
		int x = 0;
		int zeroCnt=0;
		int sameCnt=0;
		int max=0;
		
		while (x != N) {
			// 제로 수 세는데 제로를 바꿀 수 있는 기회 K보다 크면 어차피 전구 다 안들어오므로 다음 행 탐색으로 패스시킨다
			
			// 한번 들른 애들 가지친다.
			if (visited[x] == true) {
				x++;
				continue;
			}
			Pass = false;
			//자기자신포함
			sameCnt = 1;
			zeroCnt = 0;
			
			//시작 라인 0개수 세고
			for (int j = 0; j < M; j++) {
				if (arr[x][j] == 0)
					zeroCnt++;
			}
			//0 개수로 비교 시작. K보다 0개수가 크면 바꿔도 불 안들어옴
			if (zeroCnt > K) {
				x++;
				continue;
			}
			//K가 더 크거나 같더라도 홀짝 안맞으면 결국 못킨다
			else if ((zeroCnt % 2 == 0 && K % 2 == 0)==false && (zeroCnt % 2 == 1 && K % 2 == 1)==false) {
				x++;
				continue;
			}
			
			if(x!=N-1) {
				// 제로 수 오케이 나면 이제 같은 행 탐색 시작
				for (int i = x + 1; i < N; i++) {
					Pass =false;
					for (int j = 0; j < M; j++) {
						if (arr[x][j] != arr[i][j]) {
							Pass = true;
							break;
						}
					}
					if (Pass == true)
						continue;
					visited[i] = true;
					sameCnt++;
				}
			}
			
			
			if(sameCnt>max)
				max=sameCnt;
			
			x++;

		}

		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}

}
