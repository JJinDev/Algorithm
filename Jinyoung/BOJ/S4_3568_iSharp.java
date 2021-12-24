import java.io.*;
import java.util.*;

/**
* @since 2021. 12. 20.
* @author Jin
* @see https://www.acmicpc.net/problem/3568
* @mem 11524kb
* @time 76ms
* @caution S4
*/

public class S4_3568_iSharp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder answer = new StringBuilder();
		
		String prefix = st.nextToken();
		
		while(st.hasMoreTokens()) {
			Stack<Character> stack = new Stack<>();
			String s = st.nextToken();
			for (int i = 0; i < s.length(); i++) 
				stack.add(s.charAt(i));
			
			stack.pop();
			answer.append(prefix);
			while(!stack.isEmpty()){
				char c = stack.pop();
				if(c==']') {
					answer.append("[]");
					stack.pop();
				}
				else if(c=='*' || c=='&')
					answer.append(c);
				else {
					StringBuilder temp = new StringBuilder();
					answer.append(" ");
					temp.append(c);
					while(!stack.isEmpty()) 
						temp.append(stack.pop());
					answer.append(temp.reverse());
				}
			}
			answer.append(";\n");
		}
		
		System.out.println(answer);
	}
	
}
