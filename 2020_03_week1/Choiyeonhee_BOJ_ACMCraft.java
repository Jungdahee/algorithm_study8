import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_Craft {

	static int N,K,W;
	static int[] time;
	static int[] result;
	static int[] indegree;
	static ArrayList<Integer>[] arr; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int testcase=0;testcase<T;testcase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			arr = new ArrayList[N+1];
			result = new int[N+1];
			indegree = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<=N;i++) {
				arr[i] = new ArrayList<Integer>();
			}
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				arr[start].add(end); // arraylist로 건물 그래프 관리 
				indegree[end]++; // 몇개를 거쳐야 자신에게 올 수 있는지 체크 
			}
			
			W = Integer.parseInt(br.readLine()); // 승리하기 위해 지어야할 건물 
			bfs();
		}
	}
	
	static void bfs() {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int i=1;i<=N;i++) {
			result[i] = time[i]; // 각 건물에 대한 소요시간 더해주기 
			if(indegree[i]==0) queue.offer(i); // 처음지을 건물 먼저 넣어주기 
		}
		
		// 건물의 소요시간 = 이전까지의 건물 + 현재 건물의 소요시간 
		while(!queue.isEmpty()) {
			
			int current = queue.poll(); // 처음에 1이 저장 
			for(int a : arr[current]) { // 다음지을 건물 2 3 4
				result[a] = Math.max(result[a], result[current]+time[a]);
				indegree[a]--;
				
				if(indegree[a]==0) queue.offer(a);
			}
		}
		System.out.println(result[W]);
	}
}
