import java.util.*;

class L2_프린터 {
    Queue<Task> queue = new LinkedList<>();
    class Task {
        int priority;
        boolean isTarget;
        
        public Task(int priority, boolean isTarget) {
            this.priority = priority;
            this.isTarget = isTarget;
        }
    }
    
    public int solution(int[] priorities, int location) {
        init(priorities, location);
        return getAnswer();
    }
    
    public int getAnswer() {
        int count = 0;
        while(!queue.isEmpty()) {
            Task current = queue.poll();
            boolean flag = false;
            
            for (Task next : queue) {
                if(current.priority < next.priority) {
                    flag = true;
                }
            }
            
            if (flag) {
                queue.offer(current);
            } else {
                count++;
                if (current.isTarget) {
                    return count;
                }
            }
        }
        return -1;
    }
    
    public void init(int[] priorities, int location) {
        for (int i = 0; i < priorities.length; i++) {
            if (i == location) {
                queue.offer(new Task(priorities[i], true));
            } else {
                queue.offer(new Task(priorities[i], false));
            }
        }
    }
}