package BOJ.gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1520
 * 메모리 38812 kb
 * 시간 312 ms
 * 분류 다이나믹 프로그래밍, 그래프 이론, 그래프 탐색, 깊이 우선 탐색
 */
public class G4_1520_내리막길 {
    static int M, N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());

        M = Integer.parseInt(tokens.nextToken());   // 세로
        N = Integer.parseInt(tokens.nextToken());   // 가로

        map = new int[M][N];
        dp = new int[M][N];

        for (int r = 0; r < M; r++) {
            tokens = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                dp[r][c] = -1;
            }
        }

        System.out.println(solution(0, 0));
    }

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int solution(int r, int c) {
        if (r == M - 1 && c == N - 1) return 1;
        if (dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
            if (map[nr][nc] >= map[r][c]) continue;

            dp[r][c] += solution(nr, nc);
        }

        return dp[r][c];
    }
}

/*

이동은 상하좌우 이웃한 곳끼리만 가능
제일 왼쪽 위 칸에서 제일 오른족 아래 칸으로
높이가 더 낮은 지점으로만 이동!
이동 가능한 경로의 수 H 출력
H는 10억 이하의 음이 아닌 정수

[input]
4 5             // 지도 세로 M, 가로 N
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10

[output]
3

 */