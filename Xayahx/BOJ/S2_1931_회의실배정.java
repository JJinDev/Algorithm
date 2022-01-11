import java.util.*;
import java.io.*;

public class S2_1931_회의실배정 {
	static int n;
	static List<Meeting> list = new ArrayList<>();
	
	static class Meeting implements Comparable<Meeting>{
		int start, end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Meeting o) {
			if(this.end == o.end) {
				return this.start - o.start;
			} else {
				return this.end - o.end;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		print(solve());
	}

	static void print(int answer) {
		System.out.println(answer);
	}
	
	static int solve() {
		if(list.size() == 0) {
			return 1;
		}
		
		int count = 1;
		int startTime = list.get(0).start;
		int endTime = list.get(0).end;
		
		for(int i=1; i<list.size(); i++) {
			Meeting cur = list.get(i);
			if(endTime <= cur.start) {
				endTime = cur.end;
				count++;
			}
		}
		
		return count;
	}
	
	static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.add(new Meeting(start, end));
		}
		Collections.sort(list);
	} 
}
