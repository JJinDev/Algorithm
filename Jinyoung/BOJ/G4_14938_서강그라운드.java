import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 28.
* @author Jin
* @see https://www.acmicpc.net/problem/14938
* @mem 12136kb
* @time 116ms
* @caution G4
*/

public class G4_14938_서강그라운드 {
	
	static int N, M, R;
	static int[] items;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		items = new int[N];
		map = new int[N][N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(map[i], 1500000);
		}
		
			
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int val = Integer.parseInt(st.nextToken());
			if(map[from][to] > val) {
				map[from][to] = val;
				map[to][from] = val;
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i == k) continue;
				for (int j = 0; j < N; j++) {
					if(i == j || j == k) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			int sum = items[i];
			for (int j = 0; j < N; j++) 
				if(map[i][j] <= M)
					sum += items[j];
			answer = Math.max(answer, sum);
		}
		
		System.out.println(answer);
	}
}
