package BOJ.silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/*
 * 문제링크 https://www.acmicpc.net/problem/1283
 * 메모리 11888 kb
 * 시간 88 ms
 * 분류 구현, 문자열
 */
public class S2_1283_단축키지정 {
    static StringBuilder output;
    static String[] options;
    static HashSet<Character> shortcut;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        output = new StringBuilder();
        int N = Integer.parseInt(input.readLine());

        options = new String[N];
        shortcut = new HashSet<>();

        for (int n = 0; n < N; n++) {
            options[n] = input.readLine();

            if (check1(options[n])) continue;
            if (check2(options[n])) continue;
            output.append(options[n]).append("\n");

        }
        System.out.println(output);
    }

    public static boolean check1(String option) {
        // 1. 단어의 첫 글자
        String[] words = option.split(" ");
        for (int i = 0; i < words.length; i++) {
            char alphabet = words[i].toLowerCase().charAt(0);
            if (!shortcut.contains(alphabet)) {
                shortcut.add(alphabet);
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        output.append(String.format("[%c]%s ",
                                words[i].charAt(0), words[i].substring(1)));
                    } else {
                        output.append(words[j]).append(" ");
                    }
                }
                output.append("\n");
                return true;
            }
        }
        return false;
    }

    public static boolean check2(String option) {
        // 2. 왼쪽에서부터 차례대로
        for (int m = 0; m < option.length(); m++) {
            char alphabet = option.toLowerCase().charAt(m);
            if (alphabet == ' ') continue;
            if (!shortcut.contains(alphabet)) {
                shortcut.add(alphabet);
                String start = option.substring(0, m);
                String end = option.substring(m + 1);
                output.append(String.format("%s[%c]%s\n",
                        start, option.charAt(m), end));
                return true;
            }
        }
        return false;
    }
}

/*

옵션 N개
단축키를 의미하는 대표 알파벳 지정 (대소문자 구분 X)
1. 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다.
    만약 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정.
2. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 살펴보며 지정
3. 어떠한 것도 지정할 수 없다면 패스


[input]
5           // 옵션의 개수 N
New
Open
Save
Save As
Save All

[output]
[N]ew       // 단축키로 지정된 알파벳 좌우에 [] 괄호 씌워서 표현
[O]pen
[S]ave
Save [A]s
Sa[v]e All

 */