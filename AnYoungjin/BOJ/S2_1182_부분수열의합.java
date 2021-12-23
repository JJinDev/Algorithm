package BOJ.silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1182
 * 메모리 11476 kb
 * 시간 84 ms
 * 분류 브루트포스 알고리즘, 백트래킹
 */
public class S2_1182_부분수열의합 {
    static int N, S, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader input=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(input.readLine());
        arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(tokens.nextToken());
        }

        solution(0, 0);
        System.out.println(answer);
    }

    public static void solution(int sum, int startIdx) {
        if (sum == S && startIdx != 0) {
            ++answer;
        }

        for (int n = startIdx; n < N; n++) {
            solution(sum + arr[n], n + 1);
        }
    }
}

/*
N개의 정수로 이루어진 수열
크기가 양수인 부분수열 중에서
그 수열의 원소를 다 더한 값이 S가 되는 경우의 수
※ 부분수열 : 수열의 일부 항을 "순서대로 나열"하여 얻을 수 있는 수열

[input]
5 0             // N과 S
-7 -3 -2 5 8

[output]
1               // 합이 S가 되는 부분수열의 개수

 */