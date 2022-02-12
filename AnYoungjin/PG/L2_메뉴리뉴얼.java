import java.util.*;

class Solution {
    private List<Character> list = new ArrayList<>();
    private List<String> answerList = new ArrayList<>();
    private String[] orders;
    
    public String[] solution(String[] orders, int[] course) {
        this.orders = orders;
        
        for (String order: orders) {
            for (int i = 0; i < order.length(); i++) {
                if (list.contains(order.charAt(i))) continue;
                list.add(order.charAt(i));
            }
        }
        
        Collections.sort(list);
        
        for (int i = 0; i < course.length; i++) {
            maxCnt = 0;
            maxString = new ArrayList<>();
            selectMenu(0, 0, course[i], new char[course[i]]);
            
            // 최소 2명 이상의 손님으로부터 주문된 조합만 포함
            if (maxCnt <= 1) continue;
            
            for (String selectedCourse : maxString) {
                answerList.add(selectedCourse);
            }
        }
        
        String[] answer = new String[answerList.size()];
        answer = answerList.toArray(answer);
        Arrays.sort(answer);
        return answer;
    }
    
    
    private int maxCnt = 0;
    private List<String> maxString = new ArrayList<>();
    public void selectMenu(int startIdx, int idx, int numOfMenu, char[] menu) {
        // 메뉴 조합을 다 골랐다면
        if (idx == numOfMenu) {
            int cnt = 0;
            // 이 조합으로 주문한 손님 수 확인
            for (String order: orders) {
                boolean ordered = true;
                for (char m : menu) {
                    if (!order.contains(m + "")) {
                        ordered = false;
                        break;
                    }
                }
                // 손님 주문 내역에 이 메뉴가 포함되어있다면
                if (ordered) ++cnt;
            }
            
            if (cnt >= maxCnt) {
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    maxString.clear();
                }
                maxString.add(new String(menu));
            }
            
            return;
        }
        
        for (int i = startIdx; i < list.size(); i++) {
            menu[idx] = list.get(i);
            selectMenu(i + 1, idx + 1, numOfMenu, menu);
        }
    }
    
}