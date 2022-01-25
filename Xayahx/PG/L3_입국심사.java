import java.util.*;

class L3_입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long answer = 0;
        long first = 0;
        long last = (long) n * times[times.length - 1];
        long mid;
        
        while (first <= last) {
            mid = (first + last) / 2;
            long sum = 0L;
            
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }
            
            if (sum < n) {
                first = mid + 1;
            } else {
                last = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}