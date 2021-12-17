import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 16.
* @author Jin
* @see https://www.acmicpc.net/problem/20436
* @mem 11504kb
* @time 76ms
* @caution S4
*/

public class S4_20436_ZOAC3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int sL = (int) st.nextToken().charAt(0) - 97;
		int sR = (int) st.nextToken().charAt(0) - 97;
		String s = br.readLine();
		
		//[alphabet][왼손or오른손,x,y]
		int[][] alphabet = {{0,1,0},{1,2,4},{0,2,2},{0,1,2},{0,0,2},{0,1,3},{0,1,4},{1,1,5},{1,0,7},{1,1,6},{1,1,7},{1,1,8},
				{1,2,6},{1,2,5},{1,0,8},{1,0,9},{0,0,0},{0,0,3},{0,1,1},{0,0,4},{1,0,6},{0,2,3},{0,0,1},{0,2,1},{1,0,5},{0,2,0}};
		
		int answer = 0;
		for (int i = 0; i < s.length(); i++) {
			int c = (int) s.charAt(i) - 97;
			if(alphabet[c][0] == 0) {
				answer += (Math.abs(alphabet[c][1]-alphabet[sL][1])+Math.abs(alphabet[c][2]-alphabet[sL][2])+1);
				sL = c;
			}else {
				answer += (Math.abs(alphabet[c][1]-alphabet[sR][1])+Math.abs(alphabet[c][2]-alphabet[sR][2])+1);
				sR = c;
			}
		}
		
		System.out.println(answer);
	}
}
