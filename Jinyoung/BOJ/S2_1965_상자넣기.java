import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 26.
* @author Jin
* @see https://www.acmicpc.net/problem/1965
* @mem 11828kb
* @time 104ms
* @caution S2, dfs로 풀면 시간초과가 난다. dp로 풀면 통과.
*/

public class S2_1965_상자넣기 {
	
	static int N, answer;
	static int[] boxes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		boxes = new int[N];
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) 
			boxes[i] = Integer.parseInt(st.nextToken());
		
		//dfs(0, 0, 0); // 시간초과
		
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = boxes[0];
		answer = 1;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < dp.length; j++) {
				if(dp[j] >= boxes[i] || dp[j] == Integer.MAX_VALUE) {
					dp[j] = boxes[i];
					answer = Math.max(answer, j);
					break;
				}
			}
		}

		System.out.println(answer);
	}
	
	static void dfs(int size, int idx, int cnt) {
		if(idx == N) {
			answer = Math.max(answer, cnt);
			return;
		}
		if(boxes[idx] > size)
			dfs(boxes[idx], idx+1, cnt+1);
		dfs(size, idx+1, cnt);
	}
	
}
