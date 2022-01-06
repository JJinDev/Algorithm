import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 6.
* @author Jin
* @see https://www.acmicpc.net/problem/2096
* @mem 50776kb
* @time 340ms
* @caution G4
*/

public class G4_2096_내려가기 {
	
	static int N;
	static int[][][] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		result = new int[N][3][2];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) 
			result[0][i][0] = result[0][i][1] = Integer.parseInt(st.nextToken());
		
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 3; i++) {
				int num = Integer.parseInt(st.nextToken());
				int max = Integer.MIN_VALUE;
				int min = Integer.MAX_VALUE;
				for (int d = -1; d <= 1; d++) {
					if(isIn(i + d)) {
						max = Math.max(max, result[n - 1][i + d][0]);
						min = Math.min(min, result[n - 1][i + d][1]);
					}
				}
				result[n][i][0] = max + num;
				result[n][i][1] = min + num;
			}
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, result[N-1][i][0]);
			min = Math.min(min, result[N-1][i][1]);
		}
		
		answer.append(max).append(" ").append(min);
		System.out.println(answer.toString());	
	}
	
	static boolean isIn(int r) {
		return 0<=r&&r<3;
	}
}
