import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 10.
* @author Jin
* @see https://www.acmicpc.net/problem/11650
* @mem 61836kb
* @time 632ms
* @caution S5
*/

public class S5_11650_좌표정렬하기 {
	
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
			int diff = Integer.compare(this.x, o.x);
			return diff==0?Integer.compare(this.y, o.y):diff;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		List<Point> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);
		for(Point p : list) 
			answer.append(p.x).append(" ").append(p.y).append("\n");
		
		System.out.println(answer.toString());
	}
	
}
