import java.util.*;
import java.io.*;

class L3_등굣길 {
    boolean[][] map;
    long[][] dp;
    int[][] deltas = {{1,0}, {0,1}};
    
    public int solution(int m, int n, int[][] puddles) {
        init(m,n,puddles);
        return (int)solve(1,1,m,n);
    }
    
    public long solve(int x, int y, int m, int n) {
        if(dp[x][y] != -1) return dp[x][y];
        
        dp[x][y] = 0;
        
        for(int i=0; i<2; i++) {
            int nx = x + deltas[i][0];
            int ny = y + deltas[i][1];
            if(canGo(nx,ny,m,n)) {
                dp[x][y] += solve(nx,ny,m,n);
                dp[x][y] %= 1000000007;
            }
        }
        
        return dp[x][y];
    }
    
    public boolean canGo(int x, int y, int m, int n) {
        return isIn(x,y,m,n) && !map[x][y];  
    }
    
    public boolean isIn(int x, int y, int m, int n) {
        return x>0 && x<=m && y>0 && y<=n;    
    }
    
    public void init(int m, int n, int[][] puddles) {
        dp = new long[m+1][n+1];
        for(int i=0; i<=m; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        dp[m][n] = 1;
        
        map = new boolean[m+1][n+1];
        for(int i=0; i<puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            map[x][y] = true;
        }
    }
}