import java.util.*;
import java.io.*;

public class G3_11779_최소비용구하기2 {
	static int n,m,start,end;
	static int[] parent, dist;
	static ArrayList<Edge>[] graph;
	
	static class Edge implements Comparable<Edge>{
		int end, weight;
		
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		solve();
		print(getAnswer());
	}
	
	static void print(ArrayList<Integer> answers) {
		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).append("\n");
		sb.append(answers.size()).append("\n");
		for(int i=answers.size()-1; i>=0; i--) {
			sb.append(answers.get(i)).append(" ");
		}
		System.out.println(sb);
	}
	
	static ArrayList<Integer> getAnswer() {
		ArrayList<Integer> answers = new ArrayList<>();
		answers.add(end);
		
		int cur = end;
		while(cur != start) {
			cur = parent[cur];
			answers.add(cur);
		}
		
		return answers;
	}

	static void solve() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		boolean[] checked = new boolean[n+1];
		dist = new int[n+1];
		parent = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		queue.offer(new Edge(start, 0));
		
		while(!queue.isEmpty()) {
			Edge e = queue.poll();
			int cur = e.end;
			
			if(checked[cur]) continue;
			checked[cur] = true;
			
			for(Edge edge : graph[cur]) {
				if(dist[edge.end] > dist[cur] + edge.weight) {
					dist[edge.end] = dist[cur] + edge.weight;
					parent[edge.end] = cur;
					queue.offer(new Edge(edge.end, dist[edge.end]));
				}
			}
		}
	}

	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, weight));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}
}
