import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 29.
* @author Jin
* @see https://www.acmicpc.net/problem/7662
* @mem 441144kb
* @time 2412ms
* @caution G5, TreeMap은 자동 정렬이 되는 map이다.
*/

public class G5_7662_이중우선순위큐 {

	static int T, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());	
		TreeMap<Integer, Integer> tmap;
		
		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			tmap = new TreeMap<>();
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					if(tmap.containsKey(num)) 
						tmap.put(num, tmap.get(num)+1);
					else
						tmap.put(num, 1);
				}else {
					if(st.nextToken().equals("1")) {
						if(!tmap.isEmpty()) {
							int key = tmap.lastKey();
							if(tmap.get(key)>1)
								tmap.put(key, tmap.get(key)-1);
							else
								tmap.remove(key);
						}
							
					}else {
						if(!tmap.isEmpty()) {
							int key = tmap.firstKey();
							if(tmap.get(key)>1)
								tmap.put(key, tmap.get(key)-1);
							else
								tmap.remove(key);
						}
					}
				}
			}
			if(tmap.isEmpty())
				answer.append("EMPTY\n");
			else {
				answer.append(tmap.lastKey()).append(" ").append(tmap.firstKey()).append("\n");
			}
		}
		
		System.out.println(answer);
	}
}
