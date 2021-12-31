import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 30.
* @author Jin
* @see https://www.acmicpc.net/problem/10867
* @mem 24100kb
* @time 460ms
* @caution S5
*/

public class S5_10867_중복빼고정렬하기 {

	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!pq.contains(num))
				pq.offer(num);
		}
		
		StringBuilder answer = new StringBuilder();
		while(!pq.isEmpty()) 
			answer.append(pq.poll()).append(" ");
		
		System.out.println(answer.toString());
	}
}
