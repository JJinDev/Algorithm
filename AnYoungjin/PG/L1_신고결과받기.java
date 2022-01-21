import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> idIdx = new HashMap<>();
        Map<String, HashSet<Integer>> reportMap = new HashMap<>();
        
        for (int i = 0; i < id_list.length; i++) {
            idIdx.put(id_list[i], i);
        }
        
        for (String r : report) {
            String[] userIds = r.split(" ");
            String userId = userIds[0];     // 이용자 id
            String targetId = userIds[1];   // 신고한 id
            
            // 이전 이력이 없다면
            if (!reportMap.containsKey(targetId)) {
                // HashSet 초기화
                reportMap.put(targetId, new HashSet<>());
            }
            // 이용자 id의 index를 저장
            reportMap.get(targetId).add(idIdx.get(userId));
        }
        
        int[] answer = new int[id_list.length];
        for (String reportedUser : reportMap.keySet()) {
            int reportedCnt = reportMap.get(reportedUser).size();
            // 내가 신고한 유저가 k번 이상 신고당한 경우
            if (reportedCnt >= k) {
                for (int i : reportMap.get(reportedUser)) {
                    // 처리 결과 메일 1회 받음
                    ++answer[i];
                }
            }
        }
        
        return answer;
    }
}