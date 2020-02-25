import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ����1107_������ {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String str = br.readLine();
		String str_up = null;
		int len = str.length(); // ���峭 ��ư �� 0���϶� 

		int N = Integer.parseInt(str); // �̵��Ϸ��� ä��
		int temp = N; // N�� ���� �����ص� ���� 

		int count = Integer.parseInt(br.readLine()); // ���峭 ��ư ���� 
		int[] array = new int[count];
		int result = 0; // �����ߵǴ� ��ư �� 
		int result_up = 0;

		if(count != 0) {
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<count;i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
		}
		boolean check = true;

		if(count == 0) result = Math.min(len, Math.abs(100-N));
		else if(count == 10) {
			if(temp > 100) result = temp - 100;
			else result = 100 - temp;
		}

		// 100���� ���̿� ���ϱ� - ���� �����ִ� ä�� 
		else {
			while(true) {
				if(N>=0) {
					check = true;
					str = Integer.toString(N);
					len = str.length();
					L:for(int i=0;i<len;i++) {

						for(int j=0;j<count;j++) {
							if(str.charAt(i)-'0' == array[j]) {check = false; break L;}
						}
					}
					if(check == true) {result = temp-N+len; break;}
					N--; 
				}
				else break;
			}

			int zero = N;
			// ���ϱ⵵ ���� - ����� ���ϱ� ���� �̸��� �ٲ㼭 while�� �ϳ��� ó�����ֱ� ! 
			N = temp;

			while(true) {
				if(zero <= 0 && check == true) break;
				boolean check_up = true;
				str_up = Integer.toString(N);
				len = str_up.length();
				L:for(int i=0;i<len;i++) {

					for(int j=0;j<count;j++) {
						if(str_up.charAt(i)-'0' == array[j]) {check_up = false; break L;}
					}
				}
				if(check_up == true) {result_up = N-temp+len; break;}
				N++; 
			}

			if(temp>100) {
				if(result == 0) result = Math.min(temp-100, result_up);
				else if(result_up == 0) result = Math.min(temp-100, result);
				else {result = Math.min(temp-100, result); result = Math.min(result, result_up);}
			}
			else {
				if(result == 0) result = Math.min(100-temp, result_up);
				else if(result_up == 0) result = Math.min(100-temp, result);
				else {result = Math.min(100-temp, result); result = Math.min(result, result_up);}
			}
		}
		System.out.println(result);
	}
}