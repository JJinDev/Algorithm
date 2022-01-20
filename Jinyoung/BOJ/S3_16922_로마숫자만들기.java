import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 20.
* @author Jin
* @see https://www.acmicpc.net/problem/16922
* @mem 11936kb
* @time 88ms
* @caution S3 
*/

public class S3_16922_로마숫자만들기 {
	
	static int N;
	static int[] nums = {1,5,10,50};
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		//combi(N, new int[] {0,0,0,0});
		useQ();
		System.out.println(set.size());
	}
	
	static void useQ() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[1001];
		queue.add(0);
		int cnt = 0;
		while(cnt < N) {
			int size = queue.size();
			visited = new boolean[1001];
			for (int s = 0; s < size; s++) {
				int num = queue.poll();
				for (int d = 0; d < nums.length; d++) {
					int n = num + nums[d];
					if(!visited[n]) {
						queue.add(n);
						visited[n] = true;
					}
				}
			}
			cnt++;
		}
		
		while(!queue.isEmpty())
			set.add(queue.poll());
	}
	
	static void combi(int toChoose, int[] counted) {
		if(toChoose == 0) {
			int num = 0;
			for (int i = 0; i < counted.length; i++) 
				num += (nums[i]*counted[i]);
			set.add(num);
			return;
		}
		
		for (int i = 0; i < counted.length; i++) {
			counted[i]++;
			combi(toChoose - 1, counted);
			counted[i]--;
		}
	}
}
