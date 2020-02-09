package week03_study;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class kign_7701 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	
		
		for(int testcase=1;testcase<=T;testcase++) {
			int count = Integer.parseInt(br.readLine());	
			
			HashSet<String> set = new HashSet<String>();
			for(int i=0;i<count;i++) set.add(br.readLine());
			ArrayList<String> list = new ArrayList(set);
		
			Collections.sort(list, new Comparator<String>() {
	            public int compare(String a, String b) {
	                if(a.length() > b.length()) 
	                    return 1;
	                else if(a.length() < b.length()) 
	                    return -1;
	                else
	                    return a.compareTo(b);
	            }
	        });
		
			System.out.println("#"+testcase);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
