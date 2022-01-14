import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
* @since 2022. 1. 14.
* @author Jin
* @see https://www.acmicpc.net/problem/2870
* @mem 12528kb
* @time 104ms
* @caution S4, long보다 더 큰 무한대를 저장하는 자료형 : BigInteger, java.math 라이브러리에 존재
*/

public class S4_2870_수학숙제 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<BigInteger> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(),"abcdefghijklmnopqrstuvwxyz");
			while(st.hasMoreTokens())
				pq.add(new BigInteger(st.nextToken()));
		}
		
		while(!pq.isEmpty())
			answer.append(pq.poll()).append("\n");
		
		System.out.println(answer.toString());
	}
}
