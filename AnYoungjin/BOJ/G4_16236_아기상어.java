package BOJ.gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/16236
 * 메모리 23088 kb
 * 시간 176 ms
 * 분류 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션
 */
public class G4_16236_아기상어 {
    static int N;
    static int[][] map;
    static PriorityQueue<Fish> feeds;
    static Shark babyShark;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        feeds = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                // 아기상어라면
                if (map[i][j] == 9) {
                    // babyShark 초기화 후
                    babyShark = new Shark(i, j, 0, 2, 0);
                    // 공간은 0으로 바꿈
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            // 먹을 수 있는 물고기를 찾는다.
            findFish();

            // 먹을 수 있는 물고기가 없다면 엄마 상어에게 도움 요청
            if (feeds.isEmpty()) break;

            // 우선 순위가 가장 높은 물고기를 먹는다.
            Fish fish = feeds.poll();
            babyShark.eat(fish);
        }

        System.out.println(babyShark.time);
    }

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void findFish() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {babyShark.r, babyShark.c, 0});

        boolean[][] visited = new boolean[N][N];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;

            for (int d = 0; d < deltas.length; d++) {
                int nr = cur[0] + deltas[d][0];
                int nc = cur[1] + deltas[d][1];

                // 공간을 벗어날 수 없음
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                // 상어보다 큰 물고기가 있는 칸은 지나갈 수 없음
                if (map[nr][nc] > babyShark.size) continue;

                visited[nr][nc] = true;

                // 먹을 수 있는 물고기가 있다면 먹이 목록에 추가
                if (map[nr][nc] > 0 && map[nr][nc] < babyShark.size) {
                    feeds.add(new Fish(nr, nc, map[nr][nc], cur[2] + 1));
                }

                // 빈칸이거나 크기가 같은 물고기가 있는 칸은 지나감
                q.offer(new int[] {nr, nc, cur[2] + 1});
            }
        }
    }

    static class Shark {
        int r, c, cnt, size, time;

        Shark(int r, int c, int cnt, int size, int time) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.size = size;
            this.time = time;
        }

        public void eat(Fish fish) {
            cnt++;
            if (cnt == size) {
                size++;
                cnt = 0;
            }

            time += fish.distance;
            r = fish.r;
            c = fish.c;
            map[fish.r][fish.c] = 0;
            feeds.clear();
        }
    }

    static class Fish implements Comparable<Fish> {
        int r, c, size, distance;

        public Fish(int r, int c, int size, int distance) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            // 1. 가장 가까운 먹이가 우선순위가 가장 높다.
            if (distance != o.distance) {
                return Integer.compare(distance, o.distance);
            }
            // 2. 가장 위에 있는 물고기
            if (r != o.r) {
                return Integer.compare(r, o.r);
            }
            // 3. 가장 왼쪽에 있는 물고기
            return Integer.compare(c, o.c);
        }
    }
}
