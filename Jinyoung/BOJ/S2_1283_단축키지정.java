import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 11.
* @author Jin
* @see https://www.acmicpc.net/problem/1283
* @mem 11636kb
* @time 80ms
* @caution S2
*/

public class S2_1283_단축키지정 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		boolean[] alpha = new boolean[26];
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String option = br.readLine();
			if(map.containsKey(option)) {
				answer.append(map.get(option));
				continue;
			}
			st = new StringTokenizer(option," ");
			List<String> list = new ArrayList<>();
			while(st.hasMoreTokens())
				list.add(st.nextToken());
			
			boolean flag = false;
			StringBuilder temp = new StringBuilder();
			for(String s : list) {
				if(flag) 
					temp.append(s);
				else if(!alpha[((int) s.toLowerCase().charAt(0)) - 97]) {
					alpha[((int) s.toLowerCase().charAt(0)) - 97] = true;
					temp.append("[").append(s.charAt(0)).append("]").append(s.substring(1));
					flag = true;
				}else
					temp.append(s);
				temp.append(" ");
			}
			
			if(flag) {
				answer.append(temp);
				answer.append("\n");
				map.put(option, temp.append("\n").toString());
				continue;
			}
			
			temp = new StringBuilder();
			for(String s : list) {
				if(flag) 
					temp.append(s);
				else {
					for(int j = 0; j < s.length(); j++) {
						if(flag) {
							temp.append(s.substring(j));
							break;
						}
						else {
							if(!alpha[((int) s.toLowerCase().charAt(j)) - 97]) {
								alpha[((int) s.toLowerCase().charAt(j)) - 97] = true;
								temp.append("[").append(s.charAt(j)).append("]");
								flag = true;
							}else
								temp.append(s.charAt(j));
						}
					}
				}
				temp.append(" ");
			}
			answer.append(temp);
			answer.append("\n");
			map.put(option, temp.append("\n").toString());
		}
		System.out.println(answer.toString());
	}
}
