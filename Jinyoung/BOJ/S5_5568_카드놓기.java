import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 16.
* @author Jin
* @see https://www.acmicpc.net/problem/5568
* @mem 12664kb
* @time 104ms
* @caution S5
*/

public class S5_5568_카드놓기 {
	
	static int n, k;
	static int[] cards;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		cards = new int[n];
		set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(br.readLine());
		}
		
		permu(k, new int[k], new boolean[n]);
		System.out.println(set.size());
	}
	
	static void permu(int toChoose, int[] choosed, boolean[] visited) {
		if(toChoose == 0) {
			StringBuilder sb = new StringBuilder();
			for (int i : choosed) 
				sb.append(i);
			set.add(Integer.parseInt(sb.toString()));
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[k-toChoose] = cards[i];
				permu(toChoose-1, choosed, visited);
				visited[i] = false;
			}
		}
	}
}
