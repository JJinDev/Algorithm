package BOJ.silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1966
 * 메모리 12948 kb
 * 시간 104 ms
 * 분류 구현, 자료 구조, 시뮬레이션, 큐
 */
public class S3_1966_프린터큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokens.nextToken());   // 문서의 개수
            int M = Integer.parseInt(tokens.nextToken());   // 궁금한 문서의 현재 위치

            tokens = new StringTokenizer(input.readLine());
            Queue<Document> q = new LinkedList<>();
            for (int n = 0; n < N; n++) {
                q.offer(new Document(n, Integer.parseInt(tokens.nextToken())));
            }

            int cnt = 0;    // 인쇄 횟수
            while (!q.isEmpty()) {
                // 1. 현재 큐의 가장 앞에 있는 문서의 중요도 확인
                Document curDoc = q.peek();
                boolean flag = false;
                for (Document doc : q) {
                    if (doc.index == curDoc.index) continue;
                    // 2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
                    if (doc.priority > curDoc.priority) {
                        // Queue의 가장 뒤에 재배치 한다.
                        q.offer(q.poll());
                        flag = true;
                        break;
                    }
                }

                // 그렇지 않다면 바로 인쇄를 한다.
                if (!flag) {
                    ++cnt;
                    // 인쇄한 문자의 초기 index가 M이라면 문제에서 원한 문서
                    if (q.poll().index == M) {
                        // 현재까지 인쇄한 횟수 == M이 몇 번째로 인쇄됐는지
                        output.append(cnt).append("\n");
                        break;
                    }
                }
            }
        }

        System.out.println(output);
    }

    private static class Document {
        int index;
        int priority;

        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}

/*

인쇄 조건
1. 현재 Queue의 가장 앞에 있는 문서의 "중요도" 확인 (높을수록 중요함)
2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
    Queue의 가장 뒤에 재배치

M이 몇 번째로 인쇄되는지 출력

[input]
3           // 테케 수
1 0         // 문서의 개수 N, 궁금한 문서의 현재 Queue 위치 M
5           // N개 문서의 중요도
4 2
1 2 3 4
6 0
1 1 9 1 1 1

[output]
1           // 문서가 몇 번째로 인쇄되는지 출력
2
5

 */