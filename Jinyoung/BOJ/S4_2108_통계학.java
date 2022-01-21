import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 17.
* @author Jin
* @see https://www.acmicpc.net/problem/2108
* @mem (일반/카운팅정렬) 87248kb / 44736kb
* @time (일반/카운팅정렬) 1084ms / 332ms
* @caution S4, 카운팅정렬
*/

public class BOJ_2108_통계학 {
	
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		// 카운팅정렬
		int sum = 0;
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; // 초기 배열
		int[] counting = new int[8001];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			counting[num+4000]++;
			arr[i] = num+4000;
		}
		
		boolean isSecond = false;
		int max = 0;
		int maxNum = 0;
		for (int i = 0; i < counting.length; i++) {
			if(max == counting[i] && !isSecond) {
				isSecond = true;
				maxNum = i;
			} else if(max < counting[i]) {
				max = counting[i];
				maxNum = i;
				isSecond = false;
			}
		}
		
		// 누적합으로 변경
		for (int i = 1; i < counting.length; i++) 
			counting[i] += counting[i-1];
		
		// 정렬 결과
		int[] result = new int[N];
		for (int i = N-1; i >= 0; i--) 
			result[--counting[arr[i]]] = arr[i];
		
		answer.append(Math.round((float) sum / N)).append("\n");
		answer.append(result[N/2]-4000).append("\n");
		answer.append(maxNum-4000).append("\n");
		answer.append(result[N-1]-result[0]).append("\n");
		System.out.println(answer.toString());
	}
	
	static void Solution1() throws Exception {
		List<Integer> list = new ArrayList<>();
		Map<Integer,Integer> map = new HashMap<>();
		int sum = 0;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			list.add(num);
			map.put(num, map.get(num)==null?1:map.get(num)+1);
		}
		
		answer.append(Math.round((float) sum / list.size())).append("\n");
		
		Collections.sort(list);
		answer.append(list.get(list.size()/2)).append("\n");
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o2[1], o1[1])==0?Integer.compare(o1[0], o2[0]):Integer.compare(o2[1], o1[1]));
		for(int key : map.keySet())
			pq.add(new int[] {key,map.get(key)});
		int[] max = pq.poll();
		if(!pq.isEmpty() && max[1] == pq.peek()[1])
			max[0] = pq.poll()[0];
		answer.append(max[0]).append("\n");
		
		answer.append(list.get(list.size()-1)-list.get(0));
		System.out.println(answer.toString());
	}
}
