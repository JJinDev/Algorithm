class Solution {
    private int[] nums;
    
    public int solution(int[] nums) {
        this.nums = nums;
        
        return selectNums(0, 0, 0);
    }
    
    public int selectNums(int cnt, int startIdx, int sum) {
        int answer = 0;
        
        if (cnt == 3) {
            if (isPrime(sum)) return 1;
            return 0;
        }
        
        for (int i = startIdx; i < nums.length; i++) {
            answer += selectNums(cnt + 1, i + 1, sum + nums[i]);
        }
        
        return answer;
    }
    
    public boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}