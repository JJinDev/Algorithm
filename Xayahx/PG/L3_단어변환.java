import java.util.*;

class L3_단어변환 {
    public int solution(String begin, String target, String[] words) {
        return findWordPath(begin, target, words);
    }
    
    public boolean isEnd(String current, String target) {
        return current.equals(target);
    }   
    
    public boolean isAvailable(String current, String next) {
        char[] currentWord = current.toCharArray();
        char[] nextWord = next.toCharArray();
        
        int count = 0;
        for(int i=0; i<currentWord.length; i++) {
            if(currentWord[i] != nextWord[i]) {
                count++;
            }
        }
        
        return count == 1;
    }
    
    public int findWordPath(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.add(begin);
        int level = 0;
        
        while(!queue.isEmpty()) {
            int length = queue.size();
            
            for(int i=0; i<length; i++) {
                String current = queue.poll();
                if(isEnd(current, target)) {
                    return level;
                }
                
                for(int j=0; j<words.length; j++) {
                    if(visited[j]) continue;
                    if(isAvailable(current, words[j])) {
                        queue.offer(words[j]);
                        visited[j] = true;
                    }
                }
            }
            
            level++;
        }
        
        return 0;
    }
}