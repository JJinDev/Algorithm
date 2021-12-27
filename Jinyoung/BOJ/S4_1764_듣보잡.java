import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 27.
* @author Jin
* @see https://www.acmicpc.net/problem/1764
* @mem 25788kb
* @time 252ms
* @caution S4
*/

public class S4_1764_듣보잡 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) 
			set.add(br.readLine());
		
		PriorityQueue<String> names = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			String name = br.readLine();
			if(set.contains(name))
				names.add(name);
		}
		
		answer.append(names.size()).append("\n");
		while(!names.isEmpty())
			answer.append(names.poll()).append("\n");
		
		System.out.println(answer);
	}
}
