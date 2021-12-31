import java.util.*;
import java.io.*;

public class S3_4375_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		String input = null;
		while((input = br.readLine()) != null && input.length() != 0) {
			int n = Integer.parseInt(input);
			sb.append(getNumberOfDigits(n)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getNumberOfDigits(int n) {
		int number = 0;
		int count = 0;
		
		while(true) {
			number = number * 10 + 1;
			number %= n;
			count++;
			if(number == 0) {
				return count;
			}
		}
	} 
}
