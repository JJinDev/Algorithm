import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> log = new ArrayList<>();
        HashMap<String, String> userInfo = new HashMap<>();
        
        for (String r : record) {
            StringTokenizer tokens = new StringTokenizer(r, " ");
            String command = tokens.nextToken();
            String userId = tokens.nextToken();
            String nickname = command.equals("Leave")? "" : tokens.nextToken();
            
            if (command.equals("Enter")) {
                userInfo.put(userId, nickname);
                log.add(userId + "님이 들어왔습니다.");
                
            } else if (command.equals("Leave")) {
                log.add(userId + "님이 나갔습니다.");
                
            } else {
                userInfo.put(userId, nickname);
                
            }
        }
        
        String[] answer = new String[log.size()];
        
        int idx = 0;
        for (String l : log) {
            String userId = l.substring(0, l.indexOf("님"));
            answer[idx++] = l.replace(userId, userInfo.get(userId));
        }
        
        return answer;
    }
}
