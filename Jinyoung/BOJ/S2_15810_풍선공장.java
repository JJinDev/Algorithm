import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 13.
* @author Jin
* @see https://www.acmicpc.net/problem/15810
* @mem (pq/이분탐색) 167980kb / 122140kb
* @time (pq/이분탐색) 1100ms / 536ms
* @caution S2
*/

public class S2_15810_풍선공장 {
	
	static int N, M;
	static int[] balloon;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		balloon = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) 
			balloon[i] = Integer.parseInt(st.nextToken());
		
		// 이분탐색
		long answer = 0;
		long timeS = 0;
		long timeE = 1_000_000_000_000L;
		while(timeS <= timeE) {
			long mid = (timeS+timeE)/2;
			if(checkHalf(mid)) {
				answer = mid;
				timeE = mid - 1;
			}else {
				timeS = mid + 1;
			}
		}
		System.out.println(answer);
	}
	
	static boolean checkHalf(long time) {
		long cnt = 0;
		for (int i = 0; i < balloon.length; i++) {
			cnt += (time/balloon[i]);
			if(cnt >= M) return true;
		}
		return false;
	}
	
	static long usingPQ() {
		PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0])==0?Long.compare(o1[1], o2[1]):Long.compare(o1[0], o2[0]));
		for (int i = 0; i < N; i++) {
			pq.add(new long[] {balloon[i], balloon[i]});
		}
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			long[] temp = pq.poll();
			cnt++;
			if(cnt == M) {
				return temp[0];
			}
			pq.add(new long[] {temp[0] + temp[1], temp[1]});
		}
		return -1;
	}
}
