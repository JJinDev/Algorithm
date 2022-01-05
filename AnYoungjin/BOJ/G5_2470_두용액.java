package BOJ.gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/2470
 * 메모리 32084 kb
 * 시간 276 ms
 * 분류 정렬, 이분 탐색, 두 포인터
 */
public class G5_2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        int[] solutions = new int[N];
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        for (int n = 0; n < N; n++) {
            solutions[n] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(solutions);

        int start = 0;
        int end = N - 1;

        int answer = Integer.MAX_VALUE;
        int solution1 = solutions[start];
        int solution2 = solutions[end];

        while (start < end) {
            // 두 용액의 특성값의 합
            int solution = solutions[start] + solutions[end];

            // 절대값이 작은 값이 0에 가깝다
            if (Math.abs(answer) > Math.abs(solution)) {
                answer = solution;
                solution1 = solutions[start];
                solution2 = solutions[end];
            }

            if (solution > 0) --end;
            else if (solution < 0) ++start;
            else break;
        }

        // System.out.printf("%d %d", solution1, solution2);
        // ↑ 292 ms
        // ↓ 276 ms
        StringBuilder output = new StringBuilder();
        output.append(solution1).append(" ").append(solution2);
        System.out.println(output);
    }
}



/*

용액
산성     1 ~  1,000,000,000
알칼리성 -1 ~ -1,000,000,000

두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들자
특성값은 각 용액의 특성값의 합

[input]
5
-2 4 -99 -1 98

[output]
-99 98

 */
