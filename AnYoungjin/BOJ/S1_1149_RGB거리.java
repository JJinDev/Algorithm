package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1149
 * 메모리 12012 kb
 * 시간 84ms
 * 분류 DP
 */
public class S1_1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        int N = Integer.parseInt(input.readLine()); // 집의 수
        int[][] arr = new int[N+1][3];
        // N개의 집
        for (int n = 1; n <= N; n++) {
            tokens = new StringTokenizer(input.readLine());
            // 각 집을 R, G, B로 칠하는 비용
            for (int m = 0; m < 3; m++) {
                arr[n][m] = Integer.parseInt(tokens.nextToken());
            }
            // 현재 집을 R로 칠할 때 다음 집은 G or B
            arr[n][0] += Math.min(arr[n-1][1], arr[n-1][2]);
            arr[n][1] += Math.min(arr[n-1][0], arr[n-1][2]);
            arr[n][2] += Math.min(arr[n-1][0], arr[n-1][1]);
        }
        System.out.println(Math.min(arr[N][0], Math.min(arr[N][1], arr[N][2])));
    }
}

/*

RGB 거리에 집 N개 (1번 ~ N번 순서대로)
빨강, 초록, 파랑 중 하나의 색으로 칠해야 함
아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값 구하기
1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
2. N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
3. i (2 <= i <= N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

[input]
3           -> 집의 수 N
26 40 83    -> 집을 빨강으로 칠하는 비용
49 60 57    -> 초록
13 89 99    -> 파랑

[output]
96

 */