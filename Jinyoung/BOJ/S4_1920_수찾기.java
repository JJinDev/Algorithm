import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 27.
* @author Jin
* @see https://www.acmicpc.net/problem/1920
* @mem 61032kb
* @time 588ms
* @caution S4
*/

public class S4_1920_수찾기 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) 
			set.add(Integer.parseInt(st.nextToken()));
		
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			if(set.contains(Integer.parseInt(st.nextToken())))
				answer.append(1).append("\n");
			else
				answer.append(0).append("\n");
		}
		
		System.out.println(answer.toString());
	}
}
