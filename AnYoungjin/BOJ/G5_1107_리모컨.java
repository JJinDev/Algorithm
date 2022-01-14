package BOJ.gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1107
 * 메모리 69648 kb
 * 시간 348 ms
 * 분류 브루트포스 알고리즘
 */
public class G5_1107_리모컨 {
    static int answer = Integer.MAX_VALUE;
    static int targetChannel;
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        targetChannel = Integer.parseInt(input.readLine());
        int currentChannel = 100;

        int M = Integer.parseInt(input.readLine());
        broken = new boolean[10];
        if (M != 0) {
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            for (int m = 0; m < M; m++) {
                broken[Integer.parseInt(tokens.nextToken())] = true;
            }
        }

        // 현재 채널에서 +, -로만 이동하는 경우
        answer = Math.abs(targetChannel - currentChannel);
        if (M == 0) {
            // 고장난 버튼이 없다면 모든 숫자 입력 가능
            answer = Math.min(answer, Integer.toString(targetChannel).length());

        } else {
            solution("");

        }

        System.out.println(answer);
    }

    // 0번부터 999999번까지 눌러보면서 최소 횟수 찾기
    static void solution(String currentChannel) {
        for (int i = 0; i < broken.length; i++) {
            // 고장난 버튼은 누를 수 없다.
            if (broken[i]) continue;
            String channel = currentChannel + i;
            // 채널 길이 + 눌러야 하는 +,- 버튼 수
            answer = Math.min(answer,
                    channel.length() + Math.abs(targetChannel - Integer.parseInt(channel)));

            // 이동하려고 하는 채널은 0 <= N <= 500,000이므로
            if (channel.length() < 6) {
                solution(channel);
            }
        }

    }
}


/*

리모컨 버튼 0 ~ 9, +, -
0에서 -를 누른다면 채널은 변하지 X
채널의 개수는 무한대
N채널로 이동하려고 한다
고장난 버튼(숫자) 있음
버튼을 최소 몇 번 눌러야 하는지 출력
현재 100번

[input]
5457    // 이동하려고 하는 채널 N
3       // 고장난 버튼의 개수 M
6 7 8   // 고장난 버튼

[output]
6
=> 5 4 5 5 + +
=> 5 4 5 9 - -

 */