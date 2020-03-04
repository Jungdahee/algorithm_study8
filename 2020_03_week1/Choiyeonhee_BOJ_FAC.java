import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Flymetothe_Alpha {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int i=0;i<T;i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = (int) Long.parseLong(st.nextToken()); // 1~end-1 까지 -> 합이 end-1
			int end = (int) Long.parseLong(st.nextToken());
			int count = 0;
			int temp = 0;
			
			int dist = end-start;
			while(true) {
				if(count%2==0) temp++;
				dist -= temp;
				count++;
				if(dist<=0) break;
			}
			System.out.println(count);
		}
	}
}
