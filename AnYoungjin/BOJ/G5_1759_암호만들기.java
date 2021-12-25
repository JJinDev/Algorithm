package BOJ.gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제링크 https://www.acmicpc.net/problem/1759
 * 메모리 12092 kb
 * 시간 84 ms
 * 분류 수학, 브루트포스 알고리즘, 조합론, 백트래킹
 */
public class G5_1759_암호만들기 {
    static int L, C;
    static char[] alphabet;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        output = new StringBuilder();
        L = Integer.parseInt(tokens.nextToken());   // 암호의 길이
        C = Integer.parseInt(tokens.nextToken());   // 알파벳 종류

        alphabet = input.readLine().replace(" ", "").toCharArray();
        Arrays.sort(alphabet);
        solution(new char[L], 0, 0);
        System.out.println(output);
    }

    public static void solution(char[] selected, int startIdx, int depth) {
        if (depth == L) {
            int cnt1 = 0; int cnt2 = 0;
            for (int i = 0; i < selected.length; i++) {
                if ("aeiou".contains(selected[i]+"")) ++cnt1;
                else ++cnt2;
            }
            // 최소 한 개의 모음, 최소 두 개의 자음
            if (cnt1 < 1 || cnt2 < 2) return;

            for (char s : selected) {
                output.append(s);
            }
            output.append("\n");
            return;
        }

        for (int i = startIdx; i < C; i++) {
            selected[depth] = alphabet[i];
            solution(selected, i + 1, depth + 1);
        }
    }
}


/*
암호는 서로 다른 L개의 알파벳 소문자
- 최소 한 개의 모음 (a, e, i, o, u)
- 최소 두 개의 자음
- 알파벳은 증가하는 순서로 배열
문자의 종류는 C가지

[input]
4 6             // L, C
a t c i s w

[output]
acis
acit
aciw
acst
acsw
actw
aist
aisw
aitw
astw
cist
cisw
citw
istw

 */