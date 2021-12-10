import java.util.*;
import java.awt.Point;
import java.io.*;

public class G1_1175_배달 {
	static int n,m;
	static char[][] classroom;
	static int[][][][][] visited;
	static int[][] deltas = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static List<Point> destination = new ArrayList<>();
	static Queue<Minsik> queue = new LinkedList<>();
	
	static class Minsik {
		int r,c,dir,first,second;
		
		public Minsik(int r, int c, int dir, int first, int second) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.first = first;
			this.second = second;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		moveMinsik();
		print(-1);
	}
	
	static void print(int minTime) {
		System.out.println(minTime);
		System.exit(0);
	}
	
	static void moveMinsik() {
		while(!queue.isEmpty()) {
			Minsik m = queue.poll();
			int time = visited[m.r][m.c][m.dir][m.first][m.second];
			
			// 만약 이미 배달이 완료되었다면 시간 출력 후 끝내기
			if(m.first == 1 && m.second == 1) {
				print(visited[m.r][m.c][m.dir][1][1] - 1);
			}
			
			// 4방향 중에 이번 방향이랑 다른 곳으로 
			for(int i=0; i<4; i++) {
				if(i == m.dir) continue;
				int nr = m.r + deltas[i][0];
				int nc = m.c + deltas[i][1];
				
				// 방문 가능한 곳이면 (교실 안이면서 #가 아니어야 함)
				if(!isIn(nr,nc) || classroom[nr][nc] == '#') continue;
				// 블록이 배달지인지 체크하기
				int type = isDestination(nr,nc);
				
				// 아직 방문한 곳이 아니면 큐에 넣기
				if(type == -1) {
					checkIfVisited(nr,nc,i,m.first,m.second,time);
				} else if(type == 0) {
					checkIfVisited(nr,nc,i,1,m.second,time);
				} else {
					checkIfVisited(nr,nc,i,m.first,1,time);
				}
			}
		}
	}
	
	static void checkIfVisited(int nr, int nc, int dir, int first, int second, int time) {
		if(visited[nr][nc][dir][first][second] != 0) return;
		
		visited[nr][nc][dir][first][second] = time + 1;
		queue.offer(new Minsik(nr,nc,dir,first,second));
	}
	
	static int isDestination(int r, int c) {
		for(int i=0; i<2; i++) {
			if(r == destination.get(i).x && c == destination.get(i).y) {
				return i;
			}
		}
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<n && c>=0 && c<m;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		visited = new int[n][m][5][2][2];
		
		classroom = new char[n][m];
		
		for(int i=0; i<n; i++) {
			classroom[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(classroom[i][j] == 'S') {
					queue.offer(new Minsik(i,j,4,0,0));	
					visited[i][j][4][0][0] = 1;
				} else if(classroom[i][j] == 'C') {
					destination.add(new Point(i,j));
				}
			}
		}
	}
}
