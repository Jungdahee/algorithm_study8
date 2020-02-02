package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YooDongGyun_BOJ_1052_물병 {
	
	static int ans = 0;
	static int addWater = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);

		if (N < K)
			ans = 0;
		else if(N<0||N>10000000||K<0||K>1000)
			ans=-1;
		else
			reculsive(N,K);

		bw.write(String.valueOf(ans));
		bw.flush();
		br.close();
		bw.close();
	}

	public static void reculsive(int N,int K) {
		int cnt=0;
		int tempN=N;
		//몫이 0이 나올때까지 나누고 나머지가 1이 나온경우는 cnt++ (합쳐지지 못한 병 수)
		while(tempN/2!=0){
			if(tempN%2==0)
				tempN/=2;
			else {
				tempN/=2;
				cnt++;
			}
		}
		//가장 리터수가 큰 병을 안셌으므로 하나 추가
		cnt+=1;
		
		if(cnt<=K) {
			ans=addWater;
			return;
		}
		else {
			addWater++;
			reculsive(N+1,K);			
		}
	}

}
