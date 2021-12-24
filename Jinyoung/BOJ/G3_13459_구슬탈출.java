import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 24.
* @author Jin
* @see https://www.acmicpc.net/problem/13459
* @mem 129000kb
* @time 544ms
* @caution G3, cnt 체크 주의
*/

public class G3_13459_구슬탈출 {
	
	static int N, M;
	static char[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 위, 아, 왼, 오
	static int[] O, R, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'O')
					O = new int[] {i, j};
				else if(map[i][j] == 'R')
					R = new int[] {i, j};
				else if(map[i][j] == 'B')
					B = new int[] {i, j};
			}
		}
		
		boolean result = dfs(10, R[0], R[1], B[0], B[1]);
		if(result) System.out.println(1);
		else System.out.println(0);
	}
	
	static boolean dfs(int cnt, int rx, int ry, int bx, int by) {
		if(cnt == 0) 
			return false;
		
		for (int d = 0; d < deltas.length; d++) {
			int[] moveR, moveB = new int[2];
			if(ry == by) {
				if((d == 0 && rx < bx)||(d == 1 && rx > bx)) {
					moveR = move(rx, ry, d, 'R');
					moveB = move(bx, by, d, 'B');
					
				}else {
					moveB = move(bx, by, d, 'B');
					moveR = move(rx, ry, d, 'R');
				}
			}else if(rx == bx) {
				if((d == 2 && ry < by)||(d == 3 && ry > by)) {
					moveR = move(rx, ry, d, 'R');
					moveB = move(bx, by, d, 'B');
					
				}else {
					moveB = move(bx, by, d, 'B');
					moveR = move(rx, ry, d, 'R');
				}
			}else {
				moveR = move(rx, ry, d, 'R');
				moveB = move(bx, by, d, 'B');
			}
			
			if(moveB[0] == -1) {
				map[rx][ry] = 'R';
				map[bx][by] = 'B';
				if(moveR[0] != -1) map[moveR[0]][moveR[1]] = '.';
				continue;
			}
			else if(moveR[0] == -1)
				return true;
			
			boolean result = dfs(cnt-1, moveR[0], moveR[1], moveB[0], moveB[1]);
			if(result) return true;
			map[rx][ry] = 'R';
			map[bx][by] = 'B';
			map[moveR[0]][moveR[1]] = '.';
			map[moveB[0]][moveB[1]] = '.';
		}
		
		return false;
	}
	
	static int[] move(int x, int y, int d, char color) {
		int[] result = new int[2];
		map[x][y] = '.';
		
		while(isIn(x, y)) {
			x += deltas[d][0];
			y += deltas[d][1];
			if(map[x][y] == 'O') {
				result = new int[] {-1, -1};
				break;
			}else if(map[x][y] != '.') {
				result = new int[] {x-deltas[d][0], y-deltas[d][1]};
				map[result[0]][result[1]] = color;
				break;
			}
		}
		
		return result;
	}
	
	static boolean isIn(int nr, int nc) {
		return 0<=nr&&nr<N&&0<=nc&&nc<M;
	}
	
	static void printArray(char[][] array) {
		for (int i = 0; i < array.length; i++) 
			System.out.println(Arrays.toString(array[i]));
	}
	
}
