import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 31.
* @author Jin
* @see https://www.acmicpc.net/problem/11725
* @mem 72456kb
* @time 532ms
* @caution S2
*/

public class S2_11725_트리의부모찾기 {

	static int N;
	static List<Integer>[] nodes;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N];
		visited = new int[N];
		for (int i = 0; i < N; i++)
			nodes[i] = new ArrayList<>();
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			nodes[from].add(to);
			nodes[to].add(from);
		}
		
		visited[0] = -1;
		dfs(0);
		
		StringBuilder answer = new StringBuilder();
		for(int i = 1; i < visited.length; i++)
			answer.append(visited[i]).append("\n");
		
		System.out.println(answer.toString());
		
	}
	
	static void dfs(int idx) {
		List<Integer> list = nodes[idx];
		for (int i : list) {
			if(visited[i] == 0) {
				visited[i] = idx + 1;
				dfs(i);
			}
		}
	}
}
