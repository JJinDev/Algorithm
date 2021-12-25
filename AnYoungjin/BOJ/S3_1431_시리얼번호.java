package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 문제링크 https://www.acmicpc.net/problem/1431
 * 메모리 11648 kb
 * 시간 80 ms
 * 분류 정렬
 */
public class S3_1431_시리얼번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        String[] serialNumbers = new String[N];
        for (int n = 0; n < N; n++) {
            serialNumbers[n] = input.readLine();
        }
        Arrays.sort(serialNumbers, (o1, o2) -> {
            // 1. A와 B의 길이가 다르면 짧은 것이 먼저 온다.
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            }

            // 2. 만약 같다면, 작은 합을 가지는 것이 먼저 온다. (숫자만 더함)
            int thisSum = getNumSum(o1);
            int targetSum = getNumSum(o2);
            if (thisSum != targetSum) {
                return Integer.compare(thisSum, targetSum);
            }

            // 3. 사전 순으로 비교 (숫자는 알파벳보다 사전 순으로 작음)
            return o1.compareTo(o2);
        });

        StringBuilder output = new StringBuilder();
        for (String serialNumber : serialNumbers) {
            output.append(serialNumber).append("\n");
        }
        System.out.println(output);
    }

    private static int getNumSum(String serialNumber) {
        int sum = 0;
        for (int i = 0; i < serialNumber.length(); i++) {
            char c = serialNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                sum += c - '0';
            }
        }
        return sum;
    }
}


/*
시리얼번호 정렬하기
1. A와 B의 길이가 다르면, 짧은 것이 먼저 온다.
2. 만약 서로 길이가 같다면,
   A의 모든 자리수의 합과 B의 모든 자리수의 합을 비교해서
   작은 합을 가지는 것이 먼저 온다. (숫자인 것만 더한다)
3. 1, 2로 비교할 수 없다면
   사전 순으로 비교. 숫자가 알파벳보다 사전순으로 작다.


[input]
5       // 기타의 개수 N
ABCD
145C
A
A910
Z321

[output]
A
ABCD
Z321
145C
A910

 */