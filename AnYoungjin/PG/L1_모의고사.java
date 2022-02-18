import java.util.*;

class Solution {
    private final int[][] omrs = {{1, 2, 3, 4, 5},
                                  {2, 1, 2, 3, 2, 4, 2, 5},
                                  {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    
    public int[] solution(int[] answers) {
        List<Integer> answerList = new ArrayList<>();
        int maxScore = 0;
        
        for (int i = 0; i < 3; i++) {
            int score = 0;
            int idx = 0;
            
            for (int answer : answers) {
                if (idx == omrs[i].length) idx = 0;
                
                if (omrs[i][idx++] == answer) ++score;
            }
            
            if (score >= maxScore) {
                if (score > maxScore) {
                    maxScore = score;
                    answerList.clear();
                }
                answerList.add(i + 1);
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
}