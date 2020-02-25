import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 교각놓기 {
	
	static class Pair implements Comparable<Pair>{
		int start;
		int end;
		int height;
		public Pair(int start, int end, int height) {
			this.start = start;
			this.end = end;
			this.height = height;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.height - o.height; // 오름차순 (y좌표 기준으로)
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Pair[] array = new Pair[N];
		int result = 0;
		
		for(int i=0;i<N;i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int Y1 = Integer.parseInt(st.nextToken());
			int X1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			
			array[i] = new Pair(X1,X2,Y1);
		}
		
		Arrays.sort(array);
		
		// 교각 길이 세기 
		int temp1 = 0; int temp2 = 0;
		
		for(int i=0;i<N;i++) {
			temp1 = array[i].height;
			temp2 = array[i].height;
			for(int j=0;j<i;j++) {
				
				if(array[i].start>= array[j].start && array[i].start<=array[j].end-1) {
					temp1 = array[i].height - array[j].height;
				}
				if(array[j].start<=array[i].end-1 && array[j].end-1>=array[i].end-1) {
					temp2 = array[i].height - array[j].height;
				}
			}
			result += temp1;
			result += temp2;
		}
		
		System.out.println(result);
	}
}
