import java.util.*;

class L2_더맵게 {
    PriorityQueue<Integer> queue;
    public int solution(int[] scoville, int K) {
        init(scoville);
        return getMinMixCount(K);
    }
    
    public int getMinMixCount(int K) {
        int count = 0;
        while(!queue.isEmpty() && queue.peek() < K) {
            if(queue.size() == 1) {
                return -1;
            }
            mixTwoFoods(K);
            count++;
        }
        return count;
    }
    
    public void mixTwoFoods(int K) {
        int a = queue.poll();
        int b = queue.poll();
        int mixed = a + b * 2;
        queue.offer(mixed);
    }
    
    public void init(int[] scoville) {
        queue = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            queue.offer(scoville[i]);
        }
    }
}