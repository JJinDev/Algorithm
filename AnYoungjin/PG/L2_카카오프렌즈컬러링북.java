import java.util.*;

class Solution {
    int m, n, size;
    int[][] board;
    int numberOfArea, maxSizeOfOneArea;
    
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        this.m = m;
        this.n = n;
        board = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = picture[i][j];
            }
        }

        size = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    findArea(i, j, board[i][j]);
                    ++numberOfArea;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                    size = 0;
                }
            }
        }
                
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void findArea(int r, int c, int color) {
        board[r][c] = 0;
        ++size;
        
        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
            if (board[nr][nc] != color) continue;
            
            findArea(nr, nc, color);
        }
    }
}