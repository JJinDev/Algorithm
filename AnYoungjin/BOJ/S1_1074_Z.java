package BOJ.silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/5582
 * 메모리 11536 kb
 * 시간 88 ms
 * 분류 분할 정복, 재귀
 */
public class S1_1074_Z {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");
        int N = Integer.parseInt(tokens.nextToken());
        int r = Integer.parseInt(tokens.nextToken());
        int c = Integer.parseInt(tokens.nextToken());

        solution((int) Math.pow(2, N), r, c);
        System.out.println(answer);
    }

    public static void solution(int n, int r, int c) {
        if (n == 1) return;

        // n > 1이라면 각 위치에서 다시 Z 시작
        if (r < n / 2 && c < n / 2) {
            // 첫번째 칸 -> Z에서 좌상단
            solution(n / 2, r, c);

        } else if (r < n / 2 && c >= n / 2) {
            // 두번째 칸 -> Z에서 우상단
            // 첫번째 칸은 방문하고 왔음
            answer += (n * n) / 4;
            solution(n / 2, r, c - n / 2);

        } else if (r >= n / 2 && c < n / 2) {
            // 세번째 칸 -> Z에서 좌하단
            // 첫, 두번째 칸 방문했음
            answer += (n * n / 4) * 2;
            solution(n / 2, r - n / 2, c);

        } else {
            // 네번째 칸
            // 1, 2, 3칸 방문
            answer += (n * n / 4) * 3;
            solution(n / 2,
                    r - n / 2, c - n / 2);

        }
    }
}


/*

2^N x 2^N인 2차원 배열을 Z모양으로 탐색
N > 1인 경우, 4등분 한 후에 재귀적으로 순서대로 방문
Z1 Z2
Z3 Z4

r행 c열을 몇 번째로 방문하는지 출력

 */