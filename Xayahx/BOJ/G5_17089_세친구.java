import java.util.*;
import java.io.*;

public class G5_17089_세친구 {
	static int n,m;
	static int[] friends;
	static boolean[][] relations;
	
	public static void main(String[] args) throws IOException {
		init();
		print(findMinNumberOfFriends());
	}
	
	static void print(int min) {
		System.out.println((min == Integer.MAX_VALUE)?-1:min);
	}
	
	static int findMinNumberOfFriends() {
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				if(!relations[i][j]) continue;
				for(int k=j+1; k<=n; k++) {
					if(!relations[i][k] || !relations[j][k]) continue;
					min = Math.min(min, getTotalNumberOfFriends(i,j,k));
				}
			}
		}
		
		return min;
	}
	
	static int getTotalNumberOfFriends(int a, int b, int c) {
		return friends[a] + friends[b] + friends[c] - 6;
	}
	
	static void setFriends(int a, int b) {
		relations[a][b] = true;
		relations[b][a] = true;
		friends[a]++;
		friends[b]++;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		friends = new int[n+1];
		relations = new boolean[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			setFriends(a,b);
		}
	}
}
