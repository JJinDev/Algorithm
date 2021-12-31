package BOJ.silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/14501
 * 메모리 11752 kb
 * 시간 76 ms
 * 분류 다이나믹 프로그래밍, 브루트포스 알고리즘
 */
public class S3_14501_퇴사 {
    static int N, answer;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        arr = new int[N][2];

        for (int n = 0; n < N; n++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            arr[n][0] = Integer.parseInt(tokens.nextToken());   // T
            arr[n][1] = Integer.parseInt(tokens.nextToken());   // P
        }

        solution(0, 0);
        System.out.println(answer);
    }

    public static void solution(int price, int date) {
        // 현재까지 얻은 수익이 최댓값이면 갱신
        answer = Math.max(answer, price);

        // T일이 지나고 그 다음날 부터 새로 상담할 수 있음
        for (int n = date; n < N; n++) {
            // 현재 날짜 + 상담에 걸리는 시간이 N보다 크다면 상담 불가
            if (n + arr[n][0] > N) continue;
            solution(price + arr[n][1], n + arr[n][0]);
        }
    }
}

/*
오늘부터 N+1일째 되는 날 퇴사
남은 N일 동안 최대한 많은 상담을 하려고 한다.
상담에 걸리는 시간 T(일), 금액 P
N+1일부터는 상담을 할 수 없다.
얻을 수 있는 최대 수익을 출력

[input]
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

[output]
45


 */