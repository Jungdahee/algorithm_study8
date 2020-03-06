import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 잃어버린괄호 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] array = new int[50];
		int arr_in = 0; int oper_in = 0;
		char[] oper = new char[50];
		String temp = "";
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i))) { // 숫자이면 
				temp += str.charAt(i);
				if(i==str.length()-1) { // 마지막 숫자 넣어주기
					int num = Integer.parseInt(temp);
					array[arr_in++] = num;
				}
			}
			else { // 연산자이면 
				int num = Integer.parseInt(temp);
				array[arr_in++] = num;
				temp = ""; 
				oper[oper_in++] = str.charAt(i);
			}
		}
		
		L:for(int i=0;i<oper_in;i++) {
			if(oper[i]=='-') { // 마이너스 뒤에 모두 마이너스로 바꿔주기 
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
