import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 17.
* @author Jin
* @see https://www.acmicpc.net/problem/5972
* @mem 48920kb
* @time 492ms
* @caution G5, 다익스트라 : boolean[] visited를 만들면 오히려 시간초과가 나온다.
*/

public class G5_5972_택배배송 {

	static int N, M;
	static Map<Integer,Integer>[] map;
	
	static class Cow implements Comparable<Cow> {
		int idx;
		int value; // 누적 여물 수
		
		public Cow(int idx, int value) {
			super();
			this.idx = idx;
			this.value = value;
		}
		
		@Override
		public int compareTo(Cow o) {
			return Integer.compare(this.value, o.value);
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap[N];
		for (int n = 0; n < N; n++) 
			map[n] = new HashMap<>();
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			if((map[a].containsKey(b) && map[a].get(b) > c) || !map[a].containsKey(b)) {
				map[a].put(b, c);
				map[b].put(a, c);
			}
		}
		
		System.out.println(go());
		
	}
	
	static int go() {
		PriorityQueue<Cow> pq = new PriorityQueue<>();
		Cow init = new Cow(0, 0);
		pq.add(init);
		
		int[] feed = new int[N];
		Arrays.fill(feed, Integer.MAX_VALUE);
		feed[0] = 0;
		
		while(!pq.isEmpty()) {
			Cow cow = pq.poll();
			if(cow.idx == N-1)
				return cow.value;
			if(feed[cow.idx] < cow.value) continue;
			for (int i : map[cow.idx].keySet()) {
				int plus = cow.value+map[cow.idx].get(i);
				if(feed[i] > plus) {
					feed[i] = plus;
					pq.add(new Cow(i, plus));
				}
			}
		}	
		return -1;
	}
}
