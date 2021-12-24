import java.util.*;
import java.io.*;

public class S1_1946_신입사원 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n, number;
	static int[] score;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			init();
			selectEmployees();
			print();
		}
		System.out.println(sb);
	}
	
	static void print() {
		sb.append(number).append("\n");
	}
	
	static void selectEmployees() {
		number = 1;
		
		int currentScore = score[1];
		
		for(int i=2; i<=n; i++) {
			if(currentScore > score[i]) {
				currentScore = score[i];
				number++;
			}
		}
	}
	
	static void init() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		score = new int[n+1];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int document = Integer.parseInt(st.nextToken());
			int interview = Integer.parseInt(st.nextToken());
			score[document] = interview;
		}
	}
}
