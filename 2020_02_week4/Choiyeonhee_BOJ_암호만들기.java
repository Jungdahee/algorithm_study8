import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
	
	static int L,C;
	static char[] array;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 서로 다른 알파벳 소문자들 
		C = Integer.parseInt(st.nextToken()); // 암호의 갯수 
		
		array = new char[C];
		visited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			array[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(array);
		subset(0,0);
	}
	
	static void subset(int depth, int count) {
		
		if(depth==L) {
			// 암호 만들기 
			make();
			return;
		}
		
		for(int i=count;i<C;i++) {
			if(!visited[i]) {
				visited[i] = true;
				subset(depth+1,i+1);
				visited[i] = false;
			}
		}
	}
	
	static void make() {
		
		StringBuilder sb = new StringBuilder();
		
		char[] number = new char[L];
		int index = 0;
		
		int za = 0; // 자음 갯수
		int mo = 0; // 모음 갯수
		
		for(int i=0;i<C;i++) {
			if(visited[i]) {
				// 암호 만들기
				number[index++] = array[i];
			}
		} // 4개의 암호 만들어짐 
		
		Arrays.sort(number);
		
		for(int i=0;i<L;i++) {
			if(number[i] == 'a' || number[i] == 'e' || number[i] == 'i'
					|| number[i] == 'o' || number[i] == 'u') mo++;
			else za++;
		}
		
		if(za>=2 && mo>=1) System.out.println(number);
	}
}
