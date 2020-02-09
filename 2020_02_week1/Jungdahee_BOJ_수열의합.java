import java.io.*;
import java.util.ArrayList;

public class BOJ_1024_수열의합 {
	static int n, l;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		l = Integer.parseInt(input[1]);

		calc();
	}

	public static void calc() {
		int num = l;

		while (true) {
			ArrayList<Integer> list = new ArrayList<Integer>();

			if (n % num == 0) { // l이 3글자, 5글자 등
				for (int i = 0; i < num; i++) {
					if(n / num - num / 2 + i < 0) continue;
					list.add(n / num - num / 2 + i);
				}
			} else { //17 8 - 9
				for (int i = 0; i < num; i++) {
					//System.out.println(n / num - n % num + i + 1);
					if(n / num - n % num + i + 1 < 0) continue;
					list.add(n / num - n % num + i + 1);
				}
			}

			int tSum = 0;
			for (int i = 0; i < list.size(); i++) tSum += list.get(i);
			
			if(num > 100) { 
				System.out.println(-1);
				break;
			}
			
			if (tSum == n) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(0) < 0) {
						System.out.println(-1); 
						break;
					}
					System.out.print(list.get(i) + " ");
				}
				break;
			}
			num++;
		}
	}
}
