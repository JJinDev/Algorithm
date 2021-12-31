import java.util.*;
import java.io.*;

class L3_정수삼각형 {
    int height;
    int[][] dp;
    
    public int solution(int[][] triangle) {
        init(triangle);
        solve(triangle);
        return findMaxValue();
    }
    
    public void solve(int[][] triangle) {
        for(int i=1; i<height; i++) {
            for(int j=0; j<=i; j++) {
                setMaxValue(i, j, triangle);
            }
        }    
    }
    
    public void setMaxValue(int h, int index, int[][] triangle) {
        if(isIn(h-1, index-1)) {
            dp[h][index] = Math.max(dp[h][index], dp[h-1][index-1] + triangle[h][index]);
        } 
        
        if(isIn(h-1, index)) {
            dp[h][index] = Math.max(dp[h][index], dp[h-1][index] + triangle[h][index]);
        }
    }
    
    public boolean isIn(int h, int index) {
        return index >= 0 && index <= h;
    }
    
    public int findMaxValue() {
        int max = 0;
        for(int i=0; i<height; i++) {
            System.out.println(dp[height-1][i]);
            max = Math.max(max, dp[height-1][i]);
        }
        return max;
    }

    public void init(int[][] triangle) {
        dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        height = triangle.length;
    }
}