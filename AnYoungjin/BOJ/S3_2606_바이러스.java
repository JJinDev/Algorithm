package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/2606
 * 메모리 11628 kb
 * 시간 80 ms
 * 분류 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 */
public class S3_2606_바이러스 {
    static int N, M, answer;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine()); // 컴퓨터의 수
        M = Integer.parseInt(input.readLine()); // 연결되어 있는 컴퓨터 쌍의 수

        list = new ArrayList[N];
        visited = new boolean[N];
        for (int n = 0; n < N; n++) {
            list[n] = new ArrayList<>();
        }
        for (int m = 0; m < M; m++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken()) - 1;
            int b = Integer.parseInt(tokens.nextToken()) - 1;

            list[a].add(b);
            list[b].add(a);
        }

        countInfectedComputer(0);
        System.out.println(answer - 1);
    }

    static void countInfectedComputer(int a) {
        if (visited[a]) return;

        visited[a] = true;
        ++answer;
        for (int i : list[a]) {
            countInfectedComputer(i);
        }
    }
}


/*

한 컴퓨터가 바이러스에 걸리면
네트워크 상에서 연결되어 있는 모든 컴퓨터는 바이러스에 걸림
1번 컴퓨터가 바이러스에 걸렸다.

[input]
7       // 컴퓨터의 수
6       // 연결되어 있는 컴퓨터 쌍의 수
1 2     // 연결되어 있는 컴퓨터 번호 쌍
2 3
1 5
5 2
5 6
4 7

[output]
4       // 웜 바이러스에 걸리게 되는 컴퓨터의 수

 */