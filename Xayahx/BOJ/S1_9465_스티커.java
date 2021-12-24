import java.util.*;
import java.io.*;

public class S1_9465_스티커 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[][] sticker, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			init();
			solve();
			print();
		}
		System.out.println(sb);
	}
	
	static void print() {
		sb.append(Math.max(dp[0][n-1], dp[1][n-1])).append("\n");
	}
	
	static void solve() {
		for(int i=2; i<n; i++) {
			dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + sticker[0][i];
			dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + sticker[1][i];
		}
	}
	
	static void init() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		dp = new int[2][n];
		sticker = new int[2][n];
		for(int i=0; i<2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				sticker[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = sticker[0][0];
		dp[1][0] = sticker[1][0];
		if(n > 1) {
			dp[0][1] = sticker[1][0] + sticker[0][1];
			dp[1][1] = sticker[0][0] + sticker[1][1];
		}
	}
}