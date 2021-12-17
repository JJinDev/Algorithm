import java.util.*;
import java.io.*;

public class S2_1713_후보추천하기 {
	static int n;
	static List<Student> frames = new ArrayList<>();
	
	static class Student implements Comparable<Student>{
		int number, count, order;
		
		public Student(int number, int count, int order) {
			this.number = number;
			this.count = count;
			this.order = order;
		}
		
		public int compareTo(Student o) {
			if(this.count == o.count) {
				return this.order - o.order;
			}
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int length = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<length; i++) {
			int number = Integer.parseInt(st.nextToken());
			addStudent(number, i);
		}
		
		print();
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		List<Integer> answer = new ArrayList<>();
		for(int i=0; i<frames.size(); i++) {
			answer.add(frames.get(i).number);
		}
		Collections.sort(answer);
		
		for(int i=0; i<answer.size(); i++) {
			sb.append(answer.get(i)).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void addStudent(int number, int order) {
		Collections.sort(frames);
		
		int index = getIndexOfStudent(number);
		
		// 비어있는 사진틀이 없는 경우
		if(frames.size() == n) {
			if(index == -1) {
				frames.remove(0);
				frames.add(new Student(number, 1, order));
			} else {
				frames.get(index).count++;
			}
		} else {
			if(index == -1) {
				frames.add(new Student(number, 1, order));
			} else {
				frames.get(index).count++;
			}
		}
	}
	
	static int getIndexOfStudent(int number) {
		for(int i=0; i<frames.size(); i++) {
			if(frames.get(i).number == number) {
				return i;
			}
		}
		return -1;
	}
}
