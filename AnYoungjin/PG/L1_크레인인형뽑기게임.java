import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
        Stack<Integer> basket = new Stack<>();
        
        for (int move : moves) {
            for (int i = 0; i < N; i++) {
                int grid = board[i][move - 1];
                if (grid == 0) continue;
                
                if (!basket.isEmpty() && basket.peek() == grid) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(grid);
                }

                board[i][move - 1] = 0;
                break;
            }
        }
        
        return answer;
    }
}