package S05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seominseong_BOJ_알고스팟 {

	static class Info implements Comparable<Info>{
		int r;
		int c;
		int cnt;
		
		public Info(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Info arg0) {
			return this.cnt - arg0.cnt;
		}
		
	}
	
	private static int C;
	private static int R;

	// 상 하 좌 우
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 맵 크기 입력
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 맵 입력, String 으로 그대로 받기
		String map[] = new String[R];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine();
		}
		
		// 백트래킹용 배열(해당 칸 까지 가는데 들었던 최소 비용 저장)
		int[][] memory = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				memory[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// BFS 에 우선순위큐를 쓰면 더 빠르대
		Queue<Info> queue = new PriorityQueue<Info>();
		queue.add(new Info(0, 0, 0));
		memory[0][0] = 0;
		while(!queue.isEmpty()) {
			Info v = queue.poll();
			
			int r = v.r;
			int c = v.c;
			int cnt = v.cnt;
			
			for(int i = 0; i < 4; i++) {
				int mr = r + dr[i];
				int mc = c + dc[i];
				
				// 범위검사
				if(mr < 0 || mr >= R || mc < 0 || mc >= C)
					continue;
				
				// cnt 검사
				// 이미 같은 비용 또는 더 적은 비용으로 들렀다면 continue
				int mcnt = cnt;
				if(map[mr].charAt(mc) == '1')
					mcnt++;
				if(memory[mr][mc] <= mcnt)
					continue;
				
				// 큐에 넣기
				memory[mr][mc] = mcnt;
				queue.add(new Info(mr, mc, mcnt));
			}
		}
		
		// 출력
		bw.write(String.valueOf(memory[R - 1][C - 1]));
		
		bw.flush();
		bw.close();
		br.close();
	}
	
}
