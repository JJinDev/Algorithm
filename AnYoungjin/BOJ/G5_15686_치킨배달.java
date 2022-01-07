package BOJ.gold.g5;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.*;

/*
 * 문제링크 https://www.acmicpc.net/problem/15686
 * 메모리 13200 kb
 * 시간 140 ms
 * 분류 구현, 브루트포스 알고리즘
 */
public class G5_15686_치킨배달 {
    static int[][] map;
    static List<Point> houses, chickens;
    static Point[] selectedChickens;
    static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];

        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        selectedChickens = new Point[M];

        for (int r = 0; r < N; r++) {
            tokens = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                if (map[r][c] == 1) houses.add(new Point(r, c));
                if (map[r][c] == 2) chickens.add(new Point(r, c));
            }
        }

        answer = Integer.MAX_VALUE;
        // 폐업시키지 않을 치킨집 M개 골라주기
        selectChickens(0, 0);
        System.out.println(answer);
    }

    private static void selectChickens(int cnt, int startIdx) {
        if (cnt == M) {
            // 치킨집이 M개 남았다면 도시 치킨 거리 구해서 최솟값 갱신
            answer = Math.min(answer, getCityChickenDistance());
            return;
        }

        for (int i = startIdx; i < chickens.size(); i++) {
            selectedChickens[cnt] = chickens.get(i);
            selectChickens(cnt + 1, i + 1);
        }
    }

    private static int getCityChickenDistance() {
        int sum = 0;

        // 각 집에서
        for (int i = 0; i < houses.size(); i++) {
            Point curHouse = houses.get(i);
            int minDistance = Integer.MAX_VALUE;
            // 각 치킨집까지의 최솟값
            for (int j = 0; j < M; j++) {
                Point curChicken = selectedChickens[j];
                int distance = (Math.abs(curHouse.x - curChicken.x))
                        + (Math.abs(curHouse.y - curChicken.y));
                minDistance = Math.min(minDistance, distance);
            }
            sum += minDistance;
        }

        return sum;
    }
}

/*

NxN 크기의 도시
도시(r, c) r,c는 1부터 시작
치킨 거리는 집과 가장 가까운 치킨집 사이의 거리
도시의 치킨 거리 == 모든 집의 치킨 거리의 합
|r1 - r2| + |c1 - c2|

0: 빈칸
1: 집
2: 치킨집

폐업시키지 않을 치킨집을 최대 M개 골랐을 때
=> 치킨집이 M개가 넘는다면 M개 남기고 다 폐업시켜야 함
도시의 치킨 거리 최솟값 출력


[input]
5 3         // N M
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2

[output]
5

 */