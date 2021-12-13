import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 13.
* @author Jin
* @see https://www.acmicpc.net/problem/2776
* @mem 385272KB
* @time 1800ms
* @caution S4
*/

public class S4_2776_암기왕 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<>();
			for (int n = 0; n < N; n++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				int num = Integer.parseInt(st.nextToken());
				if(set.contains(num))
					answer.append("1\n");
				else
					answer.append("0\n");
			}
		}
		
		System.out.println(answer.toString());
	}

}
