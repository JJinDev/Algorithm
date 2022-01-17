import java.util.*;

class L2_큰수만들기 {
    public String solution(String number, int k) {
        return solve(number, k);
    }
    
    public String solve(String number, int k) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        
        for(int i=0; i<number.length(); i++) {
            if(count == k) {
                return makeNumber(number, stack, i);
            }
            
            char cur = number.charAt(i);
            while(!stack.isEmpty() && stack.peek()-'0' < cur-'0' && count < k) {
                stack.pop();
                count++;
            }
            stack.push(cur);
            
        }  
        return makeNumber(number, stack, number.length()).substring(0,number.length()-k);
    }
    
    public String makeNumber(String number, Stack<Character> stack, int index) {
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        sb.reverse();
        
        for(int i=index; i<number.length(); i++) {
            sb.append(number.charAt(i));
        }
        
        return sb.toString();
    }
}