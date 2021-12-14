import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 14.
* @author Jin
* @see https://www.acmicpc.net/problem/1107
* @mem 12088kb
* @time 116ms
* @caution G5, 브루트포스
*/

public class G5_1107_리모컨 {
	
	static boolean[] xBtn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int answer = Integer.MAX_VALUE;
		xBtn = new boolean[10];
		
		if(M > 0)
			st = new StringTokenizer(br.readLine()," ");
		for (int m = 0; m < M; m++) {
			xBtn[Integer.parseInt(st.nextToken())] = true;
		}
		
		answer = Math.abs(N-100);
		if(answer != 0) {
			for (int i = 0; i < 1000000; i++) {
				int cnt = remote(i);
				if(cnt == 0) continue;
				answer = Math.min(answer, Math.abs(N-i)+cnt);
			}
		}	
		System.out.println(answer);
	}
	
	static int remote(int num) {
		if(num == 0) 
			return xBtn[0]?0:1;
		int cnt = 0;
		while(num > 0) {
			if(xBtn[num % 10]) return 0;
			num /= 10;
			cnt++;
		}	
		return cnt;
	}
}
