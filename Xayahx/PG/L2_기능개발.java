import java.util.*;

class L2_기능개발 {
    Queue<Integer> completeTime = new LinkedList<>();
    ArrayList<Integer> list = new ArrayList<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        develop(progresses, speeds);
        deploy();
        return getAnswer();
    }
    
    public int[] getAnswer() {
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public void deploy() {
        while(!completeTime.isEmpty()) {
            int time = completeTime.poll();
            int count = 1;
            
            while(!completeTime.isEmpty() && time >= completeTime.peek()) {
                completeTime.poll();
                count++;
            }
            list.add(count); 
        }
    }
    
    public void develop(int[] progresses, int[] speeds) {
        for(int i=0; i<progresses.length; i++) {
            int requiredTime = getRequiredTime(100 - progresses[i], speeds[i]);
            completeTime.offer(requiredTime);
        }
    }
    
    public int getRequiredTime(int left, int speed) {
        int time = left / speed;
        return (left % speed == 0)?time:time+1;
    }
}