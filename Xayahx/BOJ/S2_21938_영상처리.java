import java.util.*;
import java.io.*;

public class S2_21938_영상처리 {
	static int n,m,t;
	static int[][] image;
	static int[][] deltas = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static boolean[][] isChecked;
	
	public static void main(String[] args) throws IOException {
		init();
		doImageThresholding();
		print(countNumberOfObjects());
	}

	static void print(int number) {
		System.out.println(number);
	}
	
	static int countNumberOfObjects() {
		int number = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!isChecked[i][j]) {
					dfs(i,j);
					number++;
				}
			}
		}
		return number;
	}
	
	static void dfs(int x, int y) {
		isChecked[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x+deltas[i][0];
			int ny = y+deltas[i][1];
			if(isIn(nx,ny) && !isChecked[nx][ny]) {
				dfs(nx,ny);
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
	
	static void doImageThresholding() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				image[i][j] = (image[i][j] >= t)?255:0;
				isChecked[i][j] = (image[i][j]==255)?false:true;
			}
		}
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		image = new int[n][m];
		isChecked = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int sum = 0;
				for(int k=0; k<3; k++) {
					sum += Integer.parseInt(st.nextToken());
				}
				image[i][j] = sum / 3;
			}
		}
		
		t = Integer.parseInt(br.readLine());
	}
}
