package BOJ.gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/3020
 * 메모리 29980 kb
 * 시간 240 ms
 * 분류 이분 탐색, 누적 합
 */
public class G5_3020_개똥벌레 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int H = Integer.parseInt(tokens.nextToken());

        int[] bottom = new int[H + 1];  // 석순 : 아래부터
        int[] top = new int[H + 1];     // 종유석 : 위부터
        for (int n = 0; n < N / 2; n++) {
            // N은 항상 짝수
            // 석순, 종유석 번갈아가며 등장
            ++bottom[Integer.parseInt(input.readLine())];
            ++top[Integer.parseInt(input.readLine())];
        }

        // 높이(구간)가 h일 때 그 이상은 함께 파괴함
        // 석순 기준으로 구간 h라면 bottom[h] 파괴
        for (int h = H - 1; h > 0; h--) {
            bottom[h] += bottom[h + 1];
            top[h] += top[h + 1];
        }

        int min = Integer.MAX_VALUE;    // 파괴해야 하는 장애물
        int cnt = 0;                    // 구간의 수
        // 구간 1 ~ H
        for (int h = 1; h < H + 1; h++) {
            // 석순은 bottom[h]만큼
            // 종유석은 top[H - h + 1]만큼 파괴
            int num = bottom[h] + top[H - h + 1];

            if (min > num) {
                min = num;
                cnt = 1;    // 구간의 수 1로 초기화

            } else if (min == num) {
                ++cnt;  // ++구간의 수

            }
        }

        System.out.printf("%d %d", min, cnt);
    }
}


/*
동굴의 길이 N, 높이 H
석순 종유석 석 종 번갈아가며 등장
개똥벌레는 일직선으로 지나가며 모든 장애물 파괴
개똥벌레가 파괴해야 하는 장애물의 최솟값과
그러한 구간(높이)의 수 출력

[input]
6 7
1
5
3
3
5
1

[output]
2 3

 */