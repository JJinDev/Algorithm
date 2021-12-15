import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 15.
* @author Jin
* @see https://www.acmicpc.net/problem/1713
* @mem 12112kb
* @time 92ms
* @caution S2, 문제 이해 주의, PQ는 add 할 당시의 값으로 유지되어 우선순위가 계산된다. => 값을 변경해서 계산하고 싶으면 지웠다가 다시 추가!!
*/

public class S2_1713_후보추천하기 {
	
	static class Student implements Comparable<Student>{
		int idx;
		int vote;
		int date;

		public Student(int idx, int vote, int date) {
			super();
			this.idx = idx;
			this.vote = vote;
			this.date = date;
		}

		@Override
		public int compareTo(Student o) {
			int diff = Integer.compare(this.vote, o.vote);
			return diff==0?Integer.compare(this.date, o.date):diff;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		PriorityQueue<Student> pq = new PriorityQueue<>();
		Student[] students = new Student[101];
		for (int i = 1; i < 101; i++) {
			students[i] = new Student(i,0,0);
		}

		st = new StringTokenizer(br.readLine()," ");
		for (int m = 0; m < M; m++) {
			int number = Integer.parseInt(st.nextToken());
			Student std = students[number];
			std.vote += 1;
			if(std.date == 0) {
				if(pq.size() >= N) {
					Student temp = pq.poll();
					temp.vote = 0;
					temp.date = 0;
				}
				std.date = m+1;
				pq.add(std);
			}else {
				pq.remove(std);
				pq.add(std);
			}
		}
		
		List<Integer> result = new ArrayList<>();
		while(!pq.isEmpty()) 
			result.add(pq.poll().idx);	
		Collections.sort(result);
		StringBuilder answer = new StringBuilder();
		for (int i : result) 
			answer.append(i).append(" ");
		
		System.out.println(answer);
	}
}
