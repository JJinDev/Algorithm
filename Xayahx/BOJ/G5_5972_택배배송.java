import java.util.*;
import java.io.*;

public class G5_5972_택배배송 {
	static int n,m;
	static List<Edge>[] graph;
	static int[] dist;
	
	static class Edge implements Comparable<Edge> {
		int end, weight;
		
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		print();
	}
	
	static void print() {
		System.out.println(dist[n]);
	}
	
	static void solve() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		boolean[] checked = new boolean[n+1];
		queue.offer(new Edge(1,0));
		dist[1] = 0;
		
		while(!queue.isEmpty()) {
			Edge e = queue.poll();
			int cur = e.end;
			if(checked[cur]) continue;
			checked[cur] = true;
			
			for(Edge edge : graph[cur]) {
				dist[edge.end] = Math.min(dist[edge.end], dist[cur]+edge.weight);
				queue.offer(new Edge(edge.end, dist[edge.end]));
			}
		}		
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dist = new int[n+1];
		for(int i=1; i<=n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b,c));
			graph[b].add(new Edge(a,c));
		}
	}
}
