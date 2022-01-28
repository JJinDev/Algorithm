import java.util.*;

class L2_영어끝말잇기 {
    int[] order;
    int dropout;
    HashSet<String> usedWords;
    
    public int[] solution(int n, String[] words) {
        init(n);
        playGame(n, words);
        return getAnswer();
    }
    
    public int[] getAnswer() {
        int[] answer = new int[2];
        
        if (dropout == 0) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = dropout;
            answer[1] = order[dropout];
        }
        
        return answer;
    }
    
    public void playGame(int n, String[] words) {
        int person = 1;
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            order[person]++;
            if (usedWords.contains(word) || isWrongWord(words, i, i-1)) {
                dropout = person;
                return;
            } else {
                usedWords.add(word);
                person++;
                if (person == n + 1) {
                    person = 1;
                }
            }
        }
    }
    
    public boolean isWrongWord(String[] words, int cur, int pre) {
        if (cur == 0) {
            return false;
        } else {
            return words[pre].charAt(words[pre].length() - 1) != words[cur].charAt(0);   
        }
    }
    
    public void init(int n) {
        order = new int[n+1];
        usedWords = new HashSet<>();
    }
}
