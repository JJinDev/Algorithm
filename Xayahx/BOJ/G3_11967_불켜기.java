import java.util.*;
import java.awt.Point;
import java.io.*;

public class G3_11967_불켜기 {
	static int n,m;
	static Queue<Point> queue = new LinkedList<>();
	static List<Switch> [][] switches;
	static boolean[][] isLight;
	static boolean[][] visited;
	static boolean[][] isPossible;
	static int[][] deltas = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	
	static class Switch {
		int a,b;
		
		public Switch(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		move();
		print();
	}
	
	static void print() {
		System.out.println(countNumberOfLightRoom());
	}
	
	static int countNumberOfLightRoom() {
		int count = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(isLight[i][j]) count++;
			}
		}
		
		return count;
	}
	
	static void move() {
		queue.offer(new Point(1,1));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			// 이미 방문해서 스위치 사용했던 방이라면 그냥 스킵
			if(visited[cur.x][cur.y]) continue;
			
			visited[cur.x][cur.y] = true;
			
			// 현재 방에서 갈 수 있는 곳을 저장해둔다
			checkIfRoomIsPossibleToReach(cur);
			
			// 현재 방에서 불을 킨다
			turnOnTheLight(cur.x, cur.y);
		}
	}
	
	static void checkIfRoomIsPossibleToReach(Point cur) {
		for(int i=0; i<4; i++) {
			int nx = cur.x + deltas[i][0];
			int ny = cur.y + deltas[i][1];
			
			if(isIn(nx,ny)) {
				isPossible[nx][ny] = true;
				
				if(isLight[nx][ny] && !visited[nx][ny]) {
					queue.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	static void turnOnTheLight(int x, int y) {
		for(int i=0; i<switches[x][y].size(); i++) {
			Switch next = switches[x][y].get(i);
			isLight[next.a][next.b] = true;
			
			// 불을 킨 방이 아직 방문하지 않은 방이면서 갈 수 있는 곳이면 큐에 추가
			if(isPossible[next.a][next.b] && !visited[next.a][next.b])  {
				queue.offer(new Point(next.a, next.b));
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return x>0 && x<=n && y>0 && y<=n;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		isLight = new boolean[n+1][n+1];
		isLight[1][1] = true;
		visited = new boolean[n+1][n+1];
		isPossible = new boolean[n+1][n+1];
		isPossible[1][1] = true;
		
		switches = new ArrayList[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				switches[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			switches[x][y].add(new Switch(a,b));
		}
	}

}
