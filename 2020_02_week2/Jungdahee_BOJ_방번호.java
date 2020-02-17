package BOJ;

import java.io.*;

public class BOJ_1082_방번호 {

	static int index, maxN, money, max, type, tmp[], info[][];
	static int num[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		index = Integer.parseInt(br.readLine());
		info = new int[2][index];
		String input[] = br.readLine().split(" ");
		info[0][0] = 0;
		info[1][0] = Integer.parseInt(input[0]);
		for(int i = 1; i < index; i++) {
			info[0][i] = i;
			if(info[1][i - 1] == Integer.parseInt(input[i]) || info[1][i - 1] == -1) info[1][i - 1] = -1;
			info[1][i] = Integer.parseInt(input[i]);
		}
		
		money = Integer.parseInt(br.readLine());
		int sum = 0;
		for(int i = 0; i < index; i++) {
			sum += info[1][i];
			if(sum > money) break;
			maxN++;
		}
		
		num = new int[index];
		tmp = new int[index];
		
		makeComb(0, 0, 0, 0);
		
		if(type == 0) {
			int tt = tmp[0];
			for(int i = 0; i < money; i++) {
				System.out.print(tt);
			}
		}
		else {
			for(int i = 0; i < maxN; i++) {
				System.out.print(tmp[i]);
			}
		}
	}
	
	static void makeComb(int size, int idx, int cnt, int sum) {
		if(cnt == maxN) {
			if(sum > money) return;			
			if(max <= sum) {
				int tType = 0;
				for(int i = 1; i < maxN; i++) {
					if(num[i - 1] != num[i]) tType++;
				}
				if(tType >= type) {
					for(int i = 0; i < maxN; i++) tmp[i] = num[maxN - 1 - i];
					max = sum;
					type = tType;
				}
			}
			return;
		}
		
		if(idx == index) return;
		
		while(true) {
			if(info[1][idx] != -1) break;
			idx++;
		}
		
		num[size] = idx;
		makeComb(size + 1, idx, cnt + 1, sum + info[1][idx]);
		makeComb(size, idx + 1, cnt, sum);
	}
}
