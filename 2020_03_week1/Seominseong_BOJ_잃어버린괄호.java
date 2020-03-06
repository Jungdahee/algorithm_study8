package S10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Seominseong_BOJ_잃어버린괄호 {

	private static int[] nums = new int[50];
	private static int nIdx = 0;
	private static char[] opers = new char[50];
	private static int oIdx = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		input(br);

		// Step 1 : Plus
		int sum = 0;
		int subIdx = 0;
		for (int i = 0; i < oIdx; i++) {
			char op = opers[i];
			// Plus
			if (op == '+') {
				sum += nums[i];
			}
			// Minus
			else {
				nums[subIdx++] = sum;
				sum = nums[i];
			}
		}

		// Step 2 : Minus
		int result = nums[0];
		for (int i = 1; i < subIdx; i++) {
			result -= nums[i];
		}

		bw.write(String.valueOf(result));

		bw.flush();
		bw.close();
		br.close();
	}

	private static void input(BufferedReader br) throws IOException {
		// Read line
		String str = br.readLine();
		int len = str.length();

		// Default operation ( {0 +} 55 - 50 + 40 )
		opers[oIdx++] = '+';

		// For number input
		char[] cnums = new char[10];
		int cIdx = 0;
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);

			// If number
			if (Character.isDigit(ch)) {
				cnums[cIdx++] = ch;
			}
			// If operation
			else {
				// Make number from character array
				int num = 0;
				int mul = 1;
				for (int j = cIdx - 1; j >= 0; j--) {
					int n = cnums[j] - '0';
					num += (n * mul);
					mul *= 10;
				}
				// Add number
				nums[nIdx++] = num;
				cIdx = 0;

				// Add operation
				opers[oIdx++] = ch;
			}
		}

		opers[oIdx++] = '-';

		// Add last number
		int num = 0;
		int mul = 1;
		for (int j = cIdx - 1; j >= 0; j--) {
			int n = cnums[j] - '0';
			num += (n * mul);
			mul *= 10;
		}
		nums[nIdx++] = num;
		cIdx = 0;
	}

}
