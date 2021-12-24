import java.util.*;
import java.io.*;

public class S4_3568_iSharp {
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String basic = st.nextToken();
		
		while(st.hasMoreTokens()) {
			String variable = st.nextToken();
			makeNewDeclaration(basic, variable.substring(0, variable.length()-1));
		}
		
		print();
	}
	
	static void print() {
		System.out.print(answer);
	}
	
	static void makeNewDeclaration(String basic, String variable) {
		StringBuilder sb = new StringBuilder();
		sb.append(basic);
		sb.append(getDataType(variable)).append(" ");
		sb.append(getVariableName(variable));
		answer.append(sb.toString()).append(";\n");
	}
	
	static String getVariableName(String variable) {	
		for(int i=variable.length()-1; i>=0; i--) { 
			if(!isType(variable.charAt(i))) {
				return variable.substring(0, i+1);
			}
		}
		return "";
	}
	
	static String getDataType(String variable) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=variable.length()-1; i>=0; i--) { 
			char c = variable.charAt(i);
			if(isType(c)) {
				if(c == ']') {
					sb.append("[]");
					i--;
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
	
	static boolean isType(char c) {
		return c == '[' || c == ']' || c == '&' || c == '*';
	}
}
