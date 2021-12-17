import java.util.*;
import java.io.*;
import java.awt.Point;

public class S4_20436_ZOAC3 {
	static int time;
	static String input;
	static HashMap<Character, Point> left, right;
	static char leftFinger, rightFinger;
	
	public static void main(String[] args) throws IOException {
		init();
		moveFingers();
		print();
	}
	
	static void print() {
		System.out.println(time);
	}
	
	static void moveFingers() {
		for(int i=0; i<input.length(); i++) {
			char next = input.charAt(i);
			if(left.containsKey(next)) {
				time += getMovingTime(left.get(next), left.get(leftFinger));
				leftFinger = next;
			} else {
				time += getMovingTime(right.get(next), right.get(rightFinger));
				rightFinger = next;
			}
			time += 1;
		}
	}
	
	static int getMovingTime(Point next, Point current) {
		return Math.abs(next.x - current.x) + Math.abs(next.y - current.y);
	}
	
	static void init() throws IOException {
		initMap();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		leftFinger = st.nextToken().charAt(0);
		rightFinger = st.nextToken().charAt(0);
		
		input = br.readLine();
	}
	
	static void initMap() {
		left = new HashMap<Character, Point>() {
			{	
				put('q', new Point(1,1));
				put('w', new Point(1,2));
				put('e', new Point(1,3));
				put('r', new Point(1,4));
				put('t', new Point(1,5));
				put('a', new Point(2,1));
				put('s', new Point(2,2));
				put('d', new Point(2,3));
				put('f', new Point(2,4));
				put('g', new Point(2,5));
				put('z', new Point(3,1));
				put('x', new Point(3,2));
				put('c', new Point(3,3));
				put('v', new Point(3,4));	
			}
		};
		
		right = new HashMap<Character, Point>() {
			{	
				put('y', new Point(1,6));
				put('u', new Point(1,7));
				put('i', new Point(1,8));
				put('o', new Point(1,9));
				put('p', new Point(1,10));
				put('h', new Point(2,6));
				put('j', new Point(2,7));
				put('k', new Point(2,8));
				put('l', new Point(2,9));
				put('b', new Point(3,5));
				put('n', new Point(3,6));
				put('m', new Point(3,7));	
			}
		};
	}
}
