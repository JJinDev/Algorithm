package BOJ.gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1987
 * 메모리 12392 kb
 * 시간 172 ms
 * 분류 그래프 이론, 그래프 탐색, 깊이 우선 탐색, 백트래킹
 */
public class G4_1987_알파벳 {
    private static int R, C, answer = 1;
    private static char[][] board;
    private static int[][] route;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());

        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        board = new char[R][C];
        route = new int[R][C];

        for (int r = 0; r < R; r++) {
            board[r] = input.readLine().toCharArray();
        }

        // 좌측 상단에서 시작 (0, 0)
        // 첫칸은 바로 방문
        solution(0, 0, 1 << board[0][0] - 'A', 1);
        System.out.println(answer);
    }

    private static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static void solution(int r, int c, int visited, int depth) {
        // 같은 경로로 그 칸에 도달한 적이 있다면 더이상 탐색 X
        if (route[r][c] == visited) return;
        route[r][c] = visited;

        for (int d = 0; d < deltas.length; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];

            // 보드판을 벗어날 수 없음
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            // 해당 알파벳을 지난 적이 없다면 이동 가능
            int alphabet = board[nr][nc] - 'A';
            if ((visited & 1 << alphabet) == 0) {
                answer = Math.max(answer, depth + 1);
                solution(nr, nc, visited | 1 << alphabet, depth + 1);
            }

        }
    }
}


/*

같은 알파벳이 적힌 칸을 두 번 지날 수 없다
좌측 상단에서 시작해서 말이 지날 수 있는 최대 칸 수 출력

[input]
2 4
CAAB
ADCB

[output]
3

 */