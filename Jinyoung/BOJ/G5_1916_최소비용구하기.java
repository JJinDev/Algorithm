import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 4.
* @author Jin
* @see https://www.acmicpc.net/problem/1916
* @mem 52684kb
* @time 592ms
* @caution G5, 다익스트라
*/

public class G5_1916_최소비용구하기 {
	
	static int N, M, answer;
	static Map<Integer, Integer>[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		answer = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new HashMap[N];
		for (int i = 0; i < N; i++) 
			map[i] = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			if(map[from].containsKey(to) && map[from].get(to) <= value) continue;
			map[from].put(to, value);
		}
		
		st = new StringTokenizer(br.readLine()," ");
		System.out.println(bus(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
	}
	
	static int bus(int from, int to) {
		int[] visited = new int[N];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {from, 0});
		visited[from] = 0;
		
		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			if(temp[0] == to) return temp[1];
			for(int key : map[temp[0]].keySet()) {
				int value = map[temp[0]].get(key) + temp[1];
				if(visited[key] > value) {
					pq.add(new int[] {key, value});
					visited[key] = value;
				}
			}
		}
		return -1;
	}
}
