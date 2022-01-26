import java.util.*;

class Solution {
    int cnt;
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    String[] data;
    
    public int solution(int n, String[] data) {
        this.data = data;
        this.cnt = 0;
        sayCheese("", new boolean[8]);
        return cnt;
    }
    
    public void sayCheese(String position, boolean[] selected) {
        if (position.length() == 8) {
            if (satisfied(position)) ++cnt;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (selected[i]) continue;
            
            selected[i] = true;
            sayCheese(position + friends[i], selected);
            selected[i] = false;
        }
    }
    
    public boolean satisfied(String position) {
        for (String d : data) {
            char me = d.charAt(0);
            char target = d.charAt(2);
            char operator = d.charAt(3);
            int dist = d.charAt(4) - '0';
            
            int distance = Math.abs(position.indexOf(me) - position.indexOf(target)) - 1;
            if (operator == '=' && distance != dist) return false;
            if (operator == '<' && distance >= dist) return false;
            if (operator == '>' && distance <= dist) return false;
        }
        
        return true;
    }
}