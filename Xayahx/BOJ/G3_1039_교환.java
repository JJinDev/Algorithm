import java.util.*;
import java.io.*;

public class G3_1039_교환 {
	static String n;
	static int k, max;
	static Queue<Number> queue = new LinkedList<>();
	static HashSet<Number> isChecked = new HashSet<>();
	
	static class Number {
		String number;
		int count;
		
		public Number(String number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + count;
			result = prime * result + ((number == null) ? 0 : number.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Number other = (Number) obj;
			if (!number.equals(other.number))
				return false;
			if (count != other.count)
				return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		print();
	}

	static void print() {
		System.out.println((max == Integer.MIN_VALUE)?-1:max);
	}
	
	static void solve() {
		while(!queue.isEmpty()) {
			Number current = queue.poll();
			
			if(current.count == k) {
				max = Math.max(max, Integer.parseInt(current.number));
				continue;
			}
			
			addNextNumbers(current);
		}
	}
	
	static void addNextNumbers(Number current) {
		char[] currentNumber = current.number.toCharArray();
		
		for(int i=0; i<currentNumber.length; i++) {
			for(int j=i+1; j<currentNumber.length; j++) {
				if(i == 0 && currentNumber[j] == '0') continue;
			
				Number nextNumber = new Number(makeNextNumber(currentNumber, i, j), current.count+1);
				if(!isChecked.contains(nextNumber)) {
					queue.offer(nextNumber);
					isChecked.add(nextNumber);
				}
			}
		}
	}
	
	static String makeNextNumber(char[] currentNumber, int i, int j) {
		char[] nextNumber = currentNumber.clone();
		char temp = nextNumber[i];
		nextNumber[i] = nextNumber[j];
		nextNumber[j] = temp;
		return new String(nextNumber);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = st.nextToken();
		k = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		isChecked.add(new Number(n,0));
		queue.offer(new Number(n, 0));
	}
}
