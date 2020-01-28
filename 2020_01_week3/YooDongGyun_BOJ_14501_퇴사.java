package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_14501_퇴사 {

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		
		int[] T = new int[n+2];
		int[] P = new int[n+2];
		int[] dp = new int[n+2];
		
		for(int i=1;i<=n;i++) {
			String[] str = br.readLine().split(" ");
			T[i] = Integer.parseInt(str[0]);
			P[i] = Integer.parseInt(str[1]);
		}
		
		//dp[n] : n일부터 벌 수 있는 돈
		for(int i=n;i>0;i--) {
			int day = i+T[i]; //당일 + 당일상담소모시간 . 즉 당일 맡은 상담 끝난 날
			
			if(day<=n+1) //7일 + 7일 소요1일이라 치면 최소 8
				dp[i]=Math.max(P[i]+dp[i+T[i]], dp[i+1]); //어느쪽이 더 돈을 많이 벌었는지
			else
				//상담일 초과 시 0값
				dp[i]=dp[i+1];
		}
		
		bw.write(String.valueOf(dp[1]));
	
		bw.flush();
		br.close();
		bw.close();
	}

}
