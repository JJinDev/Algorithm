import java.util.*;
import java.io.*;

public class G5_16936_나3곱2 {
	static int n;
	static boolean[] isChecked;
	static long[] input;
	static long[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		findOriginalNumbers();
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sb.append(numbers[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
		System.exit(0);
	}
	
	static void findNextNumber(int index, int depth) {
		if(isChecked[index]) return;
		
		numbers[depth] = input[index];
		if(depth == n-1) print();
		
		isChecked[index] = true;
	
		if(input[index] % 3 == 0) {
			for(int i=index-1; i>=0; i--) {
				if(input[i] == input[index] / 3 && !isChecked[i]) {
					findNextNumber(i, depth+1);
					break;
				}
			}
		}
		
		for(int i=index+1; i<n; i++) {
			if(input[i] == input[index] * 2 && !isChecked[i]) {
				findNextNumber(i, depth+1);
				break;
			}
		}
		
		isChecked[index] = false;
	}
	
	static void findOriginalNumbers() {
		numbers = new long[n];
		isChecked = new boolean[n];
		
		for(int i=0; i<n; i++) {
			findNextNumber(i, 0);
		}
	}
	
	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new long[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			input[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(input);
	}
}
