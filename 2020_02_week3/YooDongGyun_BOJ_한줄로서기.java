package month2.week3.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;

class Pair {
	int no;
	int chance;

	public Pair(int no, int chance) {
		this.no = no;
		this.chance = chance;
	}
}

public class YooDongGyun_BOJ_한줄로서기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		Pair[] arr = new Pair[n];
		String[] str = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			arr[i] = new Pair(i + 1, Integer.parseInt(str[i]));
		}
		LinkedList<Pair> list = new LinkedList<>();
		// 1일때 예외처리

		list.add(arr[n - 1]);
		int k = n - 2;
		while (k >= 0) {
			
			list.add(arr[k].chance,arr[k]);				
			
			k--;

		}
		for (int i = 0; i < n; i++) {
			bw.write(list.get(i).no+" ");
		}
		bw.flush();
		br.close();
		bw.close();

	}
}
