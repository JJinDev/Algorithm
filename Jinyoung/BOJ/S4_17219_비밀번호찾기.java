import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 23.
* @author Jin
* @see https://www.acmicpc.net/problem/17219
* @mem 63676kb
* @time 516ms
* @caution S4
*/

public class S4_17219_비밀번호찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, String> sites = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			sites.put(st.nextToken(), st.nextToken());
		}
		
		for (int i = 0; i < M; i++) 
			answer.append(sites.get(br.readLine())).append("\n");
		
		System.out.println(answer);
	}
	
}
