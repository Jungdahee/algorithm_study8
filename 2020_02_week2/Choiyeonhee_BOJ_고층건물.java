import java.util.Scanner;

public class ����1027_�����ǹ� {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // ���� �� 
		long[] array = new long [N+1];
		int i = 0; double height = 0;
		int[] count = new int[N+1]; // 1�� �ʱ�ȭ �ؾ��� 

		for(i=1;i<=N;i++) array[i] = sc.nextInt();
		// ��ǥ������ �����ϱ� 

		int index = 1; // (index, array[index][0])
		while(true) {

			if(index>N) break;

			i = index+1;
			if(i<=N) {
				double height_max = ((array[i]-array[index]) / (double)(i-index));
				for(i=index+2;i<=N;i++) { // ������ - ���� �� Ŀ����

					height = ((array[i]-array[index]) / (double)(i-index));

					if(height_max < height) {
						count[index]++;
						height_max = height;
					}
				}
			}

			i = index-1;
			if(0<i) {
				double height_min = ((array[index]-array[i]) / (double)(index-i));
				for(i=index-2;i>=1;i--) { // ���� - ���� �� �۾ƾ� �� 
					height = (array[index]-array[i]) / (double)(index-i);
					if(height_min > height) {
						count[index]++;
						height_min = height;
					}
				}
			}
			index++;
		}

		for(i=1;i<=N;i++) {
			if(i==1 || i==N) count[i] += 1;
			else count[i] += 2;
		}

		int max = count[1]; // ���� ���� ���̴� ������ 
		for(i=2;i<=N;i++) {
			if(max < count[i]) max = count[i];
		}
		if(N==1) System.out.println(0);
		else System.out.println(max);
	}
}
