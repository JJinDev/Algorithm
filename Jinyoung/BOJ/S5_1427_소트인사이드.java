import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 18.
* @author Jin
* @see https://www.acmicpc.net/problem/1427
* @mem 11588kb
* @time 80ms
* @caution S5
*/

public class S5_1427_소트인사이드 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) 
			pq.add(Integer.parseInt(s.charAt(i)+""));
		
		while(!pq.isEmpty())
			answer.append(pq.poll());
		
		System.out.println(answer.toString());
	}
}
