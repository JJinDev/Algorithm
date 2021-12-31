import java.util.*;

class L2_카펫 {
    int[] answer = new int[2];
    
    public int[] solution(int brown, int yellow) {
        solve(brown, yellow);
        return answer;
    }
    
    public void solve(int brown, int yellow) {
        int sum = brown + yellow;
        
        for(int i=3; i<=sum; i++) {
            if(sum % i != 0 || sum / i < 3) continue;
            if(isAvailable(sum / i, i, yellow)) {
                setAnswer(sum / i, i);
                return;
            }
        }
    }
    
    public boolean isAvailable(int x, int y, int yellow) {
        return (x - 2) * (y - 2) == yellow;
    }
    
    public void setAnswer(int x, int y) {
        answer[0] = Math.max(x, y);
        answer[1] = Math.min(x, y);
    }
}