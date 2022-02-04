package BOJ.gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/14938
 * 메모리 11892 kb
 * 시간 96 ms
 * 분류 그래프 이론, 다익스트라, 플로이드–와샬
 */
public class G4_14938_서강그라운드 {
    static int N, M, R;
    static int[][] dist;
    static int[] items;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        
        N = Integer.parseInt(tokens.nextToken());   // 지역의 개수
        M = Integer.parseInt(tokens.nextToken());   // 수색 범위
        R = Integer.parseInt(tokens.nextToken());   // 길의 개수

        items = new int[N + 1];
        tokens = new StringTokenizer(input.readLine());
        for (int n = 1; n <= N; n++) {
            // n 지역에 있는 아이템의 수
            items[n] = Integer.parseInt(tokens.nextToken());
        }

        dist = new int[N + 1][N + 1];
        for (int n = 0; n <= N; n++) {
            Arrays.fill(dist[n], INF);
            dist[n][n] = -1;
        }

        for (int r = 0; r < R; r++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int l = Integer.parseInt(tokens.nextToken());

            // 양방향 통행 가능
            dist[a][b] = l;
            dist[b][a] = l;
        }

        System.out.println(solution());
    }

    public static int solution() {
        floydWarshall();

        int answer = 0;
        // 예은이가 낙하하는 위치에 따라 먹을 수 있는 아이템 수가 달라짐
        for (int i = 1; i <= N; i++) {
            // i 지역에 낙하했을 때 얻을 수 있는 아이템의 수
            int itemCnt = 0;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] <= M) {
                    // 수색 범위 이내라면 아이템 습득
                    itemCnt += items[j];
                }
            }
            answer = Math.max(answer, itemCnt);
        }

        return answer;
    }

    public static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (i == k) continue;
                for (int j = 1; j <= N; j++) {
                    if (i == j || j == k) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
