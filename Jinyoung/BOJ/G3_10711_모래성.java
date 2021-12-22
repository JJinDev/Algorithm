import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 22.
* @author Jin
* @see https://www.acmicpc.net/problem/10711
* @mem 162492kb
* @time 536ms
* @caution G3 메모리 초과 조심!
*/

public class G3_10711_모래성 {
	
	static int H, W, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	static Queue<int[]> sand;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];
		sand = new LinkedList<>();
		answer = -1;
		
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				if(s.charAt(j) != '.') 
					map[i][j] = Integer.parseInt(s.charAt(j)+"");	
				else {
					sand.offer(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		bfs();
		
		System.out.println(answer);
	}
	
	static void bfs() {
		while(!sand.isEmpty()) {
			int size = sand.size();
			for (int s = 0; s < size; s++) {
				int[] temp = sand.poll();
				for (int d = 0; d < deltas.length; d++) {
					int nr = temp[0] + deltas[d][0];
					int nc = temp[1] + deltas[d][1];
					if(isIn(nr, nc)) {
						if(map[nr][nc] != 9 && map[nr][nc] != 0)
							map[nr][nc]--;
						if(map[nr][nc] <= 0 && !visited[nr][nc]) {
							sand.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
			answer++;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r&&r<H&&0<=c&&c<W;
	}
	
}
