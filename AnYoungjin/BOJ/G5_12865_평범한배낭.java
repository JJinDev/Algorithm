package BOJ.gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/12865
 * 메모리 12308 kb
 * 시간 128 ms
 * 분류 다이나믹 프로그래밍, 배낭 문제
 */
public class G5_12865_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        int[] W = new int[N + 1];
        int[] V = new int[N + 1];
        int[] arr = new int[K + 1];

        for (int n = 1; n <= N; n++) {
            String[] str = input.readLine().split(" ");
            W[n] = Integer.parseInt(str[0]);
            V[n] = Integer.parseInt(str[1]);
        }

        for (int n = 1; n <= N; n++) {
            // n번째 물품의 무게(W[n])보다 남은 무게(k)가 클 때
            // == 물품을 담을 수 있을 때
            for (int k = K; k >= W[n]; k--) {
                // 그 물품을 넣었을 때 가치와
                int a = arr[k - W[n]] + V[n];
                // 넣지 않았을 때 가치
                int b = arr[k];
                // 중 큰 값
                arr[k] = Math.max(a, b);
            }
        }

        System.out.println(arr[K]);
    }
}


/*

물품의 수 N
무게 W와 가치 V
준서가 버틸 수 있는 무게 K

배낭에 넣을 수 있는 물건들의 가치합의 최댓값 출력

[input]
4 7     // N K
6 13    // W V
4 8
3 6
5 12

[output]
14

 */