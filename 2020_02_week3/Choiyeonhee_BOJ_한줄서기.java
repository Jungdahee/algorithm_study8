import java.util.Scanner;
public class 백준1138_한줄서기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int number = sc.nextInt();
		int[] index = new int[number]; // 2 1 1 0  들어있다 
		int[] big = new int[number];

		for(int i=0;i<number;i++) index[i] = sc.nextInt();

		int index_num = 0; 

		L:while(true) {
			for(int i=0;i<index.length;i++) {
				boolean check = true;
				int count = 0;
				for(int j=0;j<index_num;j++) {
					if(big[j] > i+1) count++;
				}

				if(i == number) i--;
				for(int j=0;j<index.length;j++) {
					if(index[i] == count) {
						big[index_num++] = i+1;
						index[i] = -1;
						i = -1;
						break;
					}
				}

				for(int j=0;j<number;j++) {
					if(index[j] != -1) {check = false; break;}
				}
				if(check) break L;
			}
		}
		
		for(int i=0;i<number;i++) System.out.print(big[i]+" ");
	}
}
