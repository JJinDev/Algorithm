import java.util.*;

class Solution {
    public String solution(int n) {
        String[] numbers = {"4", "1", "2"};
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            sb.insert(0, numbers[n % 3]);
            n = (n - 1) / 3;
        }
        
        return sb.toString();
    }
}