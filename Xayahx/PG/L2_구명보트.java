import java.util.*;

class L2_구명보트 {
    
    public int solution(int[] people, int limit) {
        return solve(people, limit);
    }

    public int solve(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int min = 0;
        
        for (int i = people.length - 1; min <= i; i--) {
            if (min == i) {
                answer++;
                break;
            }
            if (people[min] + people[i] <= limit) {
                min++;
                answer++;
            } else {
                answer++;
            }
        }
        return answer;
    }
}