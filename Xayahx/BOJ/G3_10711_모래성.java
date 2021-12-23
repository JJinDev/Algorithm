import java.util.*;
import java.awt.Point;
import java.io.*;

/*
 * 모래가 없는 하나의 칸이 주변 8칸에 영향을 줌
 * 모래가 있는 칸을 큐에 넣지 말고 모래가 없는 칸을 큐에 넣는 것이 문제의 핵심!!
 */

public class G3_10711_모래성 {
	static int h,w;
	static boolean[][] checked;
	static int[][] deltas = {{1,0}, {0,1}, {-1,1},{1,-1}, {1,1}, {-1,-1}, {-1,0}, {0,-1}};
	static char[][] castle;
	static Queue<Point> blank = new LinkedList<>();
	static Queue<Point> collapsed = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		init();	
		print(solve());
	}
	
	static void print(int answer) {
		System.out.println(answer);
	}
	
	static int solve() {
		int time = 0;
		
		while(bfs()) {
			time++;
			checkCollapsed();
		}
		
		return time;
	}
	
	static void checkCollapsed() {
		while(!collapsed.isEmpty()) {
			Point sand = collapsed.poll();
			castle[sand.x][sand.y] = '.';
			checked[sand.x][sand.y] = true;
		}
	}
	
	static boolean bfs() {
		boolean flag = false;
		
		int len = blank.size();
		
		while(len-->0) {
			Point sand = blank.poll();
			
			if(canBeCollapsed(sand.x, sand.y)) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<h && y>=0 && y<w;
	}
	
	static boolean canBeCollapsed(int x, int y) {
		boolean isCollapsed = false;
		
		for(int i=0; i<8; i++) {
			int nx = x + deltas[i][0];
			int ny = y + deltas[i][1];
			
			if(!isIn(nx,ny)) continue;
			if(checked[nx][ny]) continue;
			
			if(castle[nx][ny] != '.') {
				castle[nx][ny]--;
				if(castle[nx][ny] == '0') {
					collapsed.offer(new Point(nx,ny));
					blank.offer(new Point(nx,ny));
					isCollapsed = true;
				}
			}
		}
		
		return isCollapsed;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		castle = new char[h][w];
		checked = new boolean[h][w];
		
		for(int i=0; i<h; i++) {
			String s = br.readLine();
			for(int j=0; j<w; j++) {
				char c = s.charAt(j);
				if(c == '.') {
					blank.offer(new Point(i,j));
					checked[i][j] = true;
				}
				castle[i][j] = c;
			}
		}
	}
}
