import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 21.
* @author Jin
* @see https://www.acmicpc.net/problem/15688
* @mem 131896kb
* @time 17072ms
* @caution S5
*/

public class S5_15688_수정렬하기5 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[2000001];
		
		for (int i = 0; i < N; i++) 
			nums[Integer.parseInt(br.readLine())+1000000]++;
		
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] > 0) {
				for (int j = 0; j < nums[i]; j++) 
					answer.append(i-1000000).append("\n");
			}
		}
		
		System.out.println(answer.toString());
	}
}
