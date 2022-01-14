import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 13.
* @author Jin
* @see https://www.acmicpc.net/problem/21921
* @mem 38424kb
* @time 288ms
* @caution S3, 투포인터
*/

public class S3_21921_블로그 {
	
	static int N, X;
	static int[] blog;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		blog = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) 
			blog[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int maxSum = 0;
		int maxCnt = 0;
		while(end < N) {
			if(end - start + 1 < X) sum += blog[end++];
			else {
				sum += blog[end++];
				if(sum > maxSum) {
					maxSum = sum;
					maxCnt = 1;
				}else if(sum == maxSum) {
					maxCnt++;
				}
				sum -= blog[start++];
			}
		}
		
		if(maxSum == 0) System.out.println("SAD");
		else {
			answer.append(maxSum).append("\n").append(maxCnt);
			System.out.println(answer.toString());
		}
	}
	
	
}
