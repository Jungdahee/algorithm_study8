import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class �Ҿ������ȣ {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] array = new int[50];
		int arr_in = 0; int oper_in = 0;
		char[] oper = new char[50];
		String temp = "";
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i))) { // �����̸� 
				temp += str.charAt(i);
				if(i==str.length()-1) { // ������ ���� �־��ֱ�
					int num = Integer.parseInt(temp);
					array[arr_in++] = num;
				}
			}
			else { // �������̸� 
				int num = Integer.parseInt(temp);
				array[arr_in++] = num;
				temp = ""; 
				oper[oper_in++] = str.charAt(i);
			}
		}
		
		L:for(int i=0;i<oper_in;i++) {
			if(oper[i]=='-') { // ���̳ʽ� �ڿ� ��� ���̳ʽ��� �ٲ��ֱ� 
				for(int j=i;j<oper_in;j++) {
					oper[j] = '-';
				}
				break L;
			}
		}
		
		int result = array[0];
		for(int i=0;i<arr_in-1;i++) {
			if(oper[i]=='-') {
				result -= array[i+1];
			}
			else {
				result += array[i+1];
			}
		}
		
		System.out.println(result);
	}
}
