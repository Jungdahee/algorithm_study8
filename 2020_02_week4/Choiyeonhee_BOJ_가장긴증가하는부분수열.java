import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열 {
	
	static int N;
	static int[] array;
	static int[] dp;
	static int max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 1;
		
		for(int i=1;i<N;i++) {
			dp[i] = 1;
			for(int j=0;j<i;j++) {
				if(array[j]<array[i] && dp[i]<dp[j]+1) {
					dp[i] = dp[j]+1;
				}
			}
		}
		
		for(int i=0;i<N;i++) max = Math.max(max, dp[i]);
		
		System.out.println(max);
	}
}
