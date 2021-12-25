package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1012
 * 메모리 13608 kb
 * 시간 128 ms
 * 분류 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 */
public class S2_1012_유기농배추 {
    static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            M = Integer.parseInt(tokens.nextToken());   // 배추밭 가로 길이
            N = Integer.parseInt(tokens.nextToken());   // 배추밭 세로 길이
            int K = Integer.parseInt(tokens.nextToken());   // 배추 개수

            int[][] map = new int[N][M];
            for (int k = 0; k < K; k++) {
                tokens = new StringTokenizer(input.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                map[y][x] = 1;
            }

            int answer = 0;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[n][m] == 1) {
                        ++answer;
                        dfs(map, n, m);
                    }
                }
            }
            output.append(answer).append("\n");
        }

        System.out.println(output);
    }

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void dfs(int[][] map, int n, int m) {
        map[n][m] = 2;

        for (int d = 0; d < deltas.length; d++) {
            int nx = n + deltas[d][0];
            int ny = m + deltas[d][1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (map[nx][ny] != 1) continue;
            dfs(map, nx, ny);
        }
    }
}



/*
해충으로부터 배추 보호
배추흰지렁이는 인접한 다른 배추로 이동 가능
필요한 최소 배추흰지렁이 마리수 출력

[input]
1           // TC
5 3 6       // 가로(M), 세로(N), 위치 개수(K)
0 2         // 배추의 위치 X, Y -> K개
1 2
2 2
3 2
4 2
4 0

[output]
2           // 필요한 최소 배추흰지렁이

 */