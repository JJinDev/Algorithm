import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 28.
* @author Jin
* @see https://www.acmicpc.net/problem/1932
* @mem 24056kb
* @time 220ms
* @caution S1, 트리 탐색으로 찾으면 시간 초과 -> dp로 해결
*/

public class S1_1932_정수삼각형 {

	static int N, answer;
	static int[] nodes, sums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		nodes = new int[N];
		sums = new int[N];
		
		nodes[0] = Integer.parseInt(br.readLine());
		sums[0] = nodes[0];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i+1; j++) { 
				int num = Integer.parseInt(st.nextToken());
				if(j == 0)
					sums[j] = nodes[j] + num;
				else if(j == i)
					sums[j] = nodes[j - 1] + num;
				else 
					sums[j] = Math.max(nodes[j-1], nodes[j]) + num;
			}
			nodes = sums.clone();
		}
		
		Arrays.sort(sums);
		System.out.println(sums[N-1]);
	}
	
}
