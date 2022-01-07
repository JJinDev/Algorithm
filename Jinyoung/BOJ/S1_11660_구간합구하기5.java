import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 3.
* @author Jin
* @see https://www.acmicpc.net/problem/11660
* @mem 127224kb
* @time 716ms
* @caution S1
*/

public class S1_11660_구간합구하기5 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j < N + 1; j++) 
				map[i][j] = Integer.parseInt(st.nextToken()) + map[i-1][j] + map[i][j-1] - map[i-1][j-1];
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());		
			answer.append(map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1]).append("\n");
		}
		System.out.println(answer);
	}

}
