import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 21.
* @author Jin
* @see https://www.acmicpc.net/problem/1504
* @mem 67748kb / 57124kb (다익스트라 / 플로이드)
* @time 736ms / 1744ms (다익스트라 / 플로이드)
* @caution G4 다익스트라가 더 빠름, 플로이드 워셜을 그대로 사용하면 시간초과가 난다. => 반복문에서 양방향 그래프임을 고려하여 j=i로 시작하여 최적화 시도 => 1초 통과.
*/

public class G4_1504_특정한최단경로 {
	
	static int N, E, INF = 987654321;
	static int[] V;
	static int[][] maps;
	static List<Node>[] mapD;
	
	static class Node implements Comparable<Node>{
		int idx;
		int dist;
		public Node(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		mapD = new ArrayList[N];
		for (int i = 0; i < N; i++) 
			mapD[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());	
			mapD[from].add(new Node(to, dist));
			mapD[to].add(new Node(from, dist));
		}
		
		st = new StringTokenizer(br.readLine()," ");
		V = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		
		int s1 = Dgo(0, V[0]);
		int s2 = Dgo(0, V[1]);
		int e1 = Dgo(V[1], N-1);
		int e2 = Dgo(V[0], N-1);
		int m = Dgo(V[0], V[1]);
		
		int answer1 = s1!=-1 && m!=-1 && e1!=-1?s1+m+e1:-1;
		int answer2 = s2!=-1 && m!=-1 && e2!=-1?s2+m+e2:-1;
		
		if(answer1 == -1) System.out.println(answer2);
		else if(answer2 == -1) System.out.println(answer1);
		else System.out.println(Math.min(answer1, answer2));
		
	}
	
	static int Dgo(int start, int end) {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			for (int i = 0; i < mapD[temp.idx].size(); i++) {
				Node n = mapD[temp.idx].get(i);
				if(dist[n.idx] > n.dist + temp.dist) {
					dist[n.idx] = n.dist + temp.dist;
					pq.add(new Node(n.idx, dist[n.idx]));
				}
			}
		}
		
		if(dist[end] == INF) return -1;
		else return dist[end];
	}
	
	static void floyd() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(maps[i], INF);
			maps[i][i] = 0;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());	
			if(maps[from][to] > dist) {
				maps[from][to] = dist;
				maps[to][from] = dist;
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		V = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i==k) continue;
				for (int j = i; j < N; j++) {
					if(i==j || k==j || maps[i][k] == INF || maps[k][j] == INF) continue;
					maps[i][j] = Math.min(maps[i][j], maps[i][k] + maps[k][j]);
					maps[j][i] = maps[i][j];
				}
			}
		}
		
		int first = go(V[0], V[1]);
		int second = go(V[1], V[0]);
		
		int answer = Math.min(first, second);
		if(first == -1) System.out.println(second);
		else if(second == -1) System.out.println(first);
		else System.out.println(answer);
	}
	
	static int go(int first, int second) {
		int temp = 0;
		if(maps[0][first] == INF) return -1;
		temp += maps[0][first];
		
		if(maps[first][second] == INF) return -1;
		temp += maps[first][second];
		
		if(maps[second][N-1] == INF) return -1;
		temp += maps[second][N-1];
		
		return temp;
	}
	
}
