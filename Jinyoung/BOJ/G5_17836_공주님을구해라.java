import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 24.
* @author Jin
* @see https://www.acmicpc.net/problem/17836
* @mem 15280kb
* @time 156ms
* @caution G5
*/

public class G5_17836_공주님을구해라 {

	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static class Point{
		int x;
		int y;
		boolean sword;
		public Point(int x, int y, boolean sword) {
			super();
			this.x = x;
			this.y = y;
			this.sword = sword;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int result = bfs();
		if(result == -1)
			System.out.println("Fail");
		else
			System.out.println(result);
		
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, false));
		visited[0][0][0] = true;
		int cnt = -1;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			cnt++;
			for (int s = 0; s < size; s++) {
				Point temp = queue.poll();
				
				if(temp.x == N-1 && temp.y == M-1) return cnt;
				else if(cnt > T) return -1;
				
				for (int d = 0; d < deltas.length; d++) {
					int nr = temp.x + deltas[d][0];
					int nc = temp.y + deltas[d][1];
					if(isIn(nr, nc)) {
						if(temp.sword && !visited[nr][nc][1]) {
							queue.add(new Point(nr, nc, temp.sword));
							visited[nr][nc][1] = true;
						}else if(!temp.sword && !visited[nr][nc][0]) {
							if(map[nr][nc] == 0) {
								queue.add(new Point(nr, nc, temp.sword));
								visited[nr][nc][0] = true;
							}else if(map[nr][nc] == 2) {
								queue.add(new Point(nr, nc, true));
								visited[nr][nc][1] = true;
							}
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int nr, int nc) {
		return 0<=nr&&nr<N&&0<=nc&&nc<M;
	}
}
