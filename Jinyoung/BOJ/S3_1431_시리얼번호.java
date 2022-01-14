import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 12.
* @author Jin
* @see https://www.acmicpc.net/problem/1431
* @mem 12120kb
* @time 84ms
* @caution S3
*/

public class S3_1431_시리얼번호 {
	
	static class Guitar implements Comparable<Guitar>{
		String name;
		int cnt;
		
		public Guitar (String name) {
			this.name = name;
			for(int i = 0; i < name.length(); i++) {
				char c = name.charAt(i);
				if((int) c < (int) 'A') 
					this.cnt += Integer.parseInt(c+"");
			}
		}

		@Override
		public int compareTo(Guitar o) {
			int diff = Integer.compare(this.name.length(), o.name.length());
			diff = diff==0?Integer.compare(this.cnt, o.cnt):diff;
			diff = diff==0?this.name.compareTo(o.name):diff;
			return diff;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Guitar> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) 
			pq.add(new Guitar(br.readLine()));
		
		while(!pq.isEmpty())
			answer.append(pq.poll().name).append("\n");
		
		System.out.println(answer.toString());
	}
}
