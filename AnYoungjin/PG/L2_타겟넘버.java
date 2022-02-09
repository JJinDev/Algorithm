class Solution {
    int[] numbers;
    int target;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        return dfs(0, 0);
    }
    
    public int dfs(int idx, int sum) {
        int answer = 0;
        
        if (idx == numbers.length) {
            if (sum == target) return 1;
            return 0;
        }
        answer += dfs(idx + 1, sum + numbers[idx]);
        answer += dfs(idx + 1, sum - numbers[idx]);
        return answer;
    }
}