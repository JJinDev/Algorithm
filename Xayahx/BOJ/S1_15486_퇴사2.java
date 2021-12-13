import java.util.*;
import java.io.*;

public class S1_15486_퇴사2 {
	static int n;
	static int[] term, pay;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		print(solve());
	}
	
	static void print(int max) {
		System.out.println(max);
	}
	
	static int solve() {
		int max = 0;
		
		// dp[i] ; i-1일까지의 최대 수익
		for(int i=1; i<=n+1; i++) {
			max = Math.max(max, dp[i]);
			if(i+term[i]-1 > n) continue;
			dp[i+term[i]] = Math.max(dp[i+term[i]], max+pay[i]);
		}
		
		return max;
	}
	
	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		term = new int[n+2];
		pay = new int[n+2];
		dp = new int[n+2];
		
		int max = 0;
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			term[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, i+term[i]);
		}
		
		dp = new int[max+1];
	}

}
