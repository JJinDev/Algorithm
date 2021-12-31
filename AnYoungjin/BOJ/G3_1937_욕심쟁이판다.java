package BOJ.gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1937
 * 메모리 59392 kb
 * 시간 436 ms
 * 분류 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색, 깊이 우선 탐색
 */
public class G3_1937_욕심쟁이판다 {
    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int n = 0; n < N; n++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            for (int m = 0; m < N; m++) {
                map[n][m] = Integer.parseInt(tokens.nextToken());
                dp[n][m] = -1;
            }
        }

        int answer = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                // map[n][m]에서 출발했을 때의 최대 이동 수
                answer = Math.max(answer, solution(n, m));
            }
        }

        System.out.println(answer);
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    public static int solution(int r, int c) {
        if (dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 1;

        int temp = 0;
        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 대나무 숲을 벗어날 수 없음
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            // 다음칸의 대나무는 더 많아야 함
            if (map[nr][nc] <= map[r][c]) continue;

            // 더 많은 이동으로 갱신
            // 상, 하, 좌, 우로 갔을 때 한 칸 더 움직이므로 + 1
            temp = Math.max(temp, solution(nr, nc));
        }
        dp[r][c] += temp;

        return dp[r][c];
    }
}


/*

n x n 크기의 대나무 숲
욕심쟁이 판다는 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동
옮긴 지역에 전 지역보다 대나무가 많이 있어야 함

판다가 이동할 수 있는 칸의 최댓값 출력


[input]
4
14 9 12 10
1 11 5 4
7 15 2 13
6 3 16 8

[output]
4

 */