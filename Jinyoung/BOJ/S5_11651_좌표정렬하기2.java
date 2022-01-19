import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 19.
* @author Jin
* @see https://www.acmicpc.net/problem/11651
* @mem 52992kb
* @time 556ms
* @caution S5
*/

public class S5_11651_좌표정렬하기2 {
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			int diff = Integer.compare(this.y, o.y);
			return diff==0?Integer.compare(this.x, o.x):diff;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			pq.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			answer.append(p.x).append(" ").append(p.y).append("\n");
		}
		System.out.println(answer.toString());
	}
}
