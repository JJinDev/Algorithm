import java.util.*;
import java.io.*;

public class S5_5568_카드놓기 {
	static int n,k;
	static int[] cards;
	static HashSet<Integer> numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		permutation(k, new boolean[n], new int[k]);
		print();
	}

	static void print() {
		System.out.println(numbers.size());
	}
	
	static void permutation(int toChoose, boolean[] choosed, int[] selectedCards) {
		if(toChoose == 0) {
			makeNewNumber(selectedCards);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(choosed[i]) continue;
			choosed[i] = true;
			selectedCards[k-toChoose] = cards[i];
			permutation(toChoose-1, choosed, selectedCards);
			choosed[i] = false;
		}
	}
	
	static void makeNewNumber(int[] selectedCards) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<k; i++) {
			sb.append(selectedCards[i]);
		}
		numbers.add(Integer.parseInt(sb.toString()));
	}
	
	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		numbers = new HashSet<>();
		cards = new int[n];
		for(int i=0; i<n; i++) {
			cards[i] = Integer.parseInt(br.readLine());
		}
	}
}