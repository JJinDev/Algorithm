import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        
        while (!pq.isEmpty()) {
            int first = pq.poll();
            if (first >= K) return answer;
            if (pq.isEmpty()) return -1;
            
            int second = pq.poll();
            pq.offer(first + second * 2);
            ++answer;
        }
        
        return -1;
    }
}