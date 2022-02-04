import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int totalTime = 0;
        int idx = 0;
        
        List<Integer> list = new ArrayList<>();
        while (idx < progresses.length) {
            // totalTime : 이 작업이 끝나기까지 필요한 일수
            totalTime = (100 - progresses[idx]) / speeds[idx];
            if ((100 - progresses[idx]) % speeds[idx] > 0) ++totalTime;
            
            // idx 번째 기능은 완료했으니 배포 가능 -> 1부터 카운트
            int deployCnt = 1;
            while (++idx < progresses.length) {
                // totalTime 만큼의 시간을 투자했을 때 완료할 수 있다면
                if (progresses[idx] + totalTime * speeds[idx] >= 100) {
                    ++deployCnt;
                } else {
                    break;
                }
            }
            
            list.add(deployCnt);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}