import java.util.*;

class L2_단체사진찍기 {
    int answer = 0;
    String[] datas;
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited = new boolean[26];
    
    public int solution(int n, String[] data) {
        datas = data.clone();
        permutation(8, new int[26], 8);
        return answer;
    }

    public boolean isAvailable(int[] choosed) {
        for(int i=0; i<datas.length; i++) {
            String data = datas[i];
            int number = data.charAt(4)-'0' + 1;
            char comparison = data.charAt(3);
            
            if(comparison == '='){
                if(Math.abs(choosed[data.charAt(0)-'A'] - choosed[data.charAt(2)-'A']) != number)
                    return false;
            }else if(comparison == '<'){
                if(Math.abs(choosed[data.charAt(0)-'A'] - choosed[data.charAt(2)-'A']) >= number)
                    return false;
            }else {
                if(Math.abs(choosed[data.charAt(0)-'A'] - choosed[data.charAt(2)-'A']) <= number)
                    return false;
            }
        }
        return true;
    }
    
    public void permutation(int toChoose, int[] choosed, int total) {
        if(toChoose == 0){
            if(isAvailable(choosed)) answer++;
            return;
        }
        
        for(int i=0; i<8; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            choosed[friends[total-toChoose]-'A'] = i;
            permutation(toChoose-1, choosed, 8);
            visited[i] = false;
        }
    }
}