import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 7.
* @author Jin
* @see https://www.acmicpc.net/problem/2342
* @mem 58404kb
* @time 424ms
* @caution G3, 분류는 DP, PQ로 풀이
*/

public class G3_2342_DanceDanceRevolution {
	
	static List<Integer> map;
	static int[][][] visited;
	static class Step implements Comparable<Step>{
		int idx;
		int left;
		int right;
		int sum;
		public Step(int idx, int left, int right, int sum) {
			super();
			this.idx = idx;
			this.left = left;
			this.right = right;
			this.sum = sum;
		}
		@Override
		public int compareTo(Step o) {
			return Integer.compare(this.sum, o.sum);
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		map = new ArrayList<>();
		while(st.hasMoreTokens())
			map.add(Integer.parseInt(st.nextToken()));
		
		visited = new int[5][5][map.size()];
		
		PriorityQueue<Step> pq = new PriorityQueue<>();
		pq.offer(new Step(-1, 0, 0, 0));
		
		while(!pq.isEmpty()) {
			Step temp = pq.poll();
			int targetIdx = map.get(temp.idx+1);
			if(targetIdx == 0) {
				System.out.println(temp.sum);
				return;
			}
			if(targetIdx != temp.right) {
				int left = cost(temp.left, targetIdx);
				if(visited[targetIdx][temp.right][temp.idx+1] == 0 || visited[targetIdx][temp.right][temp.idx+1] > left + temp.sum) {
					pq.offer(new Step(temp.idx + 1, targetIdx, temp.right, left + temp.sum));
					visited[targetIdx][temp.right][temp.idx+1] = (left + temp.sum);
				}
			}
			if(targetIdx != temp.left) {
				int right = cost(temp.right, targetIdx);
				if(visited[temp.left][targetIdx][temp.idx+1] == 0 || visited[temp.left][targetIdx][temp.idx+1] > right + temp.sum) {
					pq.offer(new Step(temp.idx + 1, temp.left, targetIdx, right + temp.sum));
					visited[temp.left][targetIdx][temp.idx+1] = (right + temp.sum);
				}
			}
		}
		
	}
	
	static int cost(int from, int to) {
		if(from == to) return 1;
		switch (from) {
		case 0:
			return 2;
		case 1:
		case 3:
			if(to == 2 || to == 4) return 3;
			else return 4;
		case 2:
		case 4:
			if(to == 1 || to == 3) return 3;
			else return 4;
		}
		return -1;
	}
	
}
