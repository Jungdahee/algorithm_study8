package week03_study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class triple_6781 {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1;testcase<=T;testcase++) {
			char[] num_R = new char[9];
			char[] num_G = new char[9];
			char[] num_B = new char[9];
			String number = br.readLine();
			String color = br.readLine();
			int index_a = 0; int index_b = 0; int index_c = 0;
			for(int i=0;i<9;i++) {
				if(color.charAt(i)=='R') num_R[index_a++] = number.charAt(i);
				else if(color.charAt(i)=='G') num_G[index_b++] = number.charAt(i);
				else if(color.charAt(i)=='B') num_B[index_c++] = number.charAt(i);	
			}
				
			if(num_R.length%3!=0 || num_G.length%3!=0 ||num_B.length%3!=0) {
				System.out.println("#"+testcase+" "+"Continue"); continue;
			}
			else {
				
				if(check(num_R,num_R.length)&&check(num_G,num_G.length)&&check(num_B,num_B.length)) System.out.println("#"+testcase+" "+"Win");
				else System.out.println("#"+testcase+" "+"Continue");
			}
		}
	}
	private static boolean check(char[] array, int length) {
		Arrays.sort(array);
		int num = 0;
		int check = 0;
		int count = 0;
		for(int i=0;i<length-2;i++) {
			if(array[i]==0) {count++; continue;}
			if(i==count) num+=(count/3);
			if((length-count)%3!=0) return false;
			if(array[i]==array[i+1]&&array[i+1]==array[i+2]) check+=1;
			else if(array[i]+1==array[i+1]&&array[i+1]+1==array[i+2]) check+=1;
			else check+=0;
			num++;
			i=num*3-1;
		}
		if(check==(length-count)/3) return true;
		else return false;
	}
}
