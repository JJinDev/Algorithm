package BOJ.silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1890
 * 메모리 11760 kb
 * 시간 92 ms
 * 분류 다이나믹 프로그래밍
 */
public class S2_1890_점프 {
    static int N;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine()); // 게임 판의 크기 N
        map = new int[N][N];
        dp = new long[N][N];

        for (int r = 0; r < N; r++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                dp[r][c] = -1;
            }
        }

        // 좌측상단에서부터 시작
        System.out.println(solution(0, 0));
    }

    public static long solution(int r, int c) {
        // 가장 오른쪽 아래 칸에 도착했다면 경로 1추가
        if (r == N - 1 && c == N - 1) return 1;

        // -1이 아니라면 이미 지나간 기록이 있음
        // 여기서부터의 경로 개수는 dp[r][c]
        if (dp[r][c] != -1) return dp[r][c];

        // 지나간 기록이 없다면 0부터 시작
        dp[r][c] = 0;

        // 아래쪽으로 이동
        int nr = r + map[r][c];
        if (nr >= 0 && nr < N) {
            dp[r][c] += solution(nr, c);
        }

        // 오른쪽으로 이동
        int nc = c + map[r][c];
        if (nc >= 0 && nc < N) {
            dp[r][c] += solution(r, nc);
        }

        return dp[r][c];
    }
}

/*

좌측상단에서 우측하단으로 규칙에 맞게 점프
반드시 오른쪽이나 아래쪽으로만 이동
칸에 적힌 숫자만큼 이동 가능
경로의 개수 출력

[input]
4
2 3 3 1
1 2 1 3
1 2 3 1
3 1 1 0

[output]
3

 */