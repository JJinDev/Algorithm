import java.util.*;
import java.io.*;

class L2_오픈채팅방 {
    public String[] solution(String[] record) {
        StringTokenizer st;
        HashMap<String, String> user = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        for(int i=0; i<record.length; i++){
            String rec = record[i];
            st = new StringTokenizer(rec);
            String first = st.nextToken();
            
            if(first.equals("Change") || first.equals("Enter")){
                user.put(st.nextToken(), st.nextToken());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<record.length; i++){
            String rec = record[i];
            sb = new StringBuilder();
            st = new StringTokenizer(rec);
            String first = st.nextToken();
            String uid = st.nextToken();
            
            if(first.equals("Enter")){
                sb.append(user.get(uid)).append("님이 들어왔습니다.");
                list.add(sb.toString());
            }else if(first.equals("Leave")){
                sb.append(user.get(uid)).append("님이 나갔습니다.");
                list.add(sb.toString());
            }
            sb.setLength(0);
        }
        
        String[] answer = new String[list.size()];
        int cnt = 0;
        for(String ans : list){
            answer[cnt++] = ans;
        }
        return answer;
    }
}