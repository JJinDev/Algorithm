package BOJ.gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제링크 https://www.acmicpc.net/problem/1238
 * 메모리 17172 kb
 * 시간 172 ms
 * 분류 그래프 이론, 다익스트라
 */
public class G3_1238_파티 {
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        X = Integer.parseInt(tokens.nextToken());

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        ArrayList<Node>[] graphR = new ArrayList[N + 1];
        for (int n = 0; n <= N; n++) {
            graphR[n] = new ArrayList<>();
            graph[n] = new ArrayList<>();
        }

        int[] dist = new int[N + 1];
        int[] distR = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(distR, Integer.MAX_VALUE);

        for (int m = 1; m <= M; m++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int time = Integer.parseInt(tokens.nextToken());

            graph[start].add(new Node(end, time));
            graphR[end].add(new Node(start, time));
        }

        solution(graph, dist);
        solution(graphR, distR);

        int answer = -1;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dist[i] + distR[i]);
        }
        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int idx, distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void solution(ArrayList<Node>[] graph, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Node(X, 0));
        dist[X] = 0;

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (visited[curNode.idx]) continue;
            visited[curNode.idx] = true;

            for (Node nextNode : graph[curNode.idx]) {
                if (dist[nextNode.idx] > dist[curNode.idx] + nextNode.distance) {
                    dist[nextNode.idx] = dist[curNode.idx] + nextNode.distance;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
    }
}
