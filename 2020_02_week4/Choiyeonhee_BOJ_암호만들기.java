import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ��ȣ����� {
	
	static int L,C;
	static char[] array;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // ���� �ٸ� ���ĺ� �ҹ��ڵ� 
		C = Integer.parseInt(st.nextToken()); // ��ȣ�� ���� 
		
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
			// ��ȣ ����� 
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
		
		int za = 0; // ���� ����
		int mo = 0; // ���� ����
		
		for(int i=0;i<C;i++) {
			if(visited[i]) {
				// ��ȣ �����
				number[index++] = array[i];
			}
		} // 4���� ��ȣ ������� 
		
		Arrays.sort(number);
		
		for(int i=0;i<L;i++) {
			if(number[i] == 'a' || number[i] == 'e' || number[i] == 'i'
					|| number[i] == 'o' || number[i] == 'u') mo++;
			else za++;
		}
		
		if(za>=2 && mo>=1) System.out.println(number);
	}
}
