import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 5.
* @author Jin
* @see https://www.acmicpc.net/problem/11779
* @mem 48940kb
* @time 480ms
* @caution G3, 다익스트라
*/

public class G3_11779_최소비용구하기2 {
	
	static int N, M;
	static Map<Integer, Integer>[] map;
	
	static class Way implements Comparable<Way>{
		int cost;
		int curr;
		List<Integer> cities;
		
		public Way(int cost, int curr, List<Integer> cities) {
			super();
			this.cost = cost;
			this.curr = curr;
			this.cities = cities;
		}

		@Override
		public int compareTo(Way o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new Map[N];
		for(int i = 0; i < N; i++)
			map[i] = new HashMap<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			if(map[from].containsKey(to) && map[from].get(to) <= value) continue;
			map[from].put(to, value);
		}
		
		st = new StringTokenizer(br.readLine());
		Way result = search(Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken()) - 1);
		answer.append(result.cost).append("\n").append(result.cities.size()).append("\n");
		for(int i : result.cities)
			answer.append(i + 1).append(" ");
		System.out.println(answer);
	}
	
	static Way search(int from, int to) {
		int[] visited = new int[N];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[from] = 0;
		
		PriorityQueue<Way> pq = new PriorityQueue<>();
		List<Integer> first = new ArrayList<>();
		first.add(from);
		pq.offer(new Way(0, from, first));
		
		while(!pq.isEmpty()) {
			Way temp = pq.poll();
			if(temp.curr == to) return temp;
			for (int key : map[temp.curr].keySet()) {
				int value = temp.cost + map[temp.curr].get(key);
				if(visited[key] > value) {
					visited[key] = value;
					List<Integer> list = new ArrayList<>();
					for(int i : temp.cities)
						list.add(i);
					list.add(key);
					pq.offer(new Way(value, key, list));
				}
			}
		}
		
		return null;
	}

}
