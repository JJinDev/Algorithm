import java.util.*;

class Solution {
    List<int[]> timeline;
    
    public int solution(String[] lines) {
        int answer = 0;
        
        timeline = new ArrayList<>();
        for (String line: lines) {
            timeline.add(convertTimetoInt(line));
        }
        
        for (int[] time: timeline) {
            // time[0]: 요청시간
            // time[1]: 응답완료시간
            answer = Math.max(answer, countRequest(time[0]));
            answer = Math.max(answer, countRequest(time[1]));
        }
        
        return answer;
    }
    
    public int countRequest(int startTime) {
        // 기준시간(startTime)부터 999(1초)동안 처리된 요청
        int start = startTime;
        int end = start + 999;
        int cnt = 0;

        for (int[] time : timeline) {
            // 요청시간이 구간에 들어가거나
            if (time[0] >= start && time[0] <= end) ++cnt;
            // 응답완료시간이 구간에 들어가거나
            else if (time[1] >= start && time[1] <= end) ++cnt;
            // 부분이 포함되어있거나
            else if (time[0] <= start && time[1] >= end) ++cnt;
        }
        
        return cnt;
    }
    
    public int[] convertTimetoInt(String line) {
        String[] str = line.split(" ");
        String S = str[1];
        String T = str[2].replace("s", "");
        
        StringTokenizer tokens = new StringTokenizer(S, ":.");
        
        int hour = Integer.parseInt(tokens.nextToken()) * 3600 * 1000;
        int min = Integer.parseInt(tokens.nextToken()) * 60 * 1000;
        int sec = Integer.parseInt(tokens.nextToken()) * 1000;
        int millisec = Integer.parseInt(tokens.nextToken());
        
        // 14일에 시작한 요청이 음수가 될 수 있으므로
        // 응답완료시간에 + 3000 (최대 처리시간이 3초이므로)
        int endTime = hour + min + sec + millisec + 3000;
        int processingTime = (int) (Double.parseDouble(T) * 1000);
        int startTime = endTime - processingTime + 1;
                
        return new int[] {startTime, endTime};
    }
}
