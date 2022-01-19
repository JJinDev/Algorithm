import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();

        // 문자열은 제일 앞부터 정해진 길이만큼 잘라야 함
        // 반이 넘어가면 압축불가
        for (int i = 1; i <= s.length() / 2; i++) {
            // 압축의 기준이 될 문자열
            String s1 = s.substring(0, i);
            String s2 = "";
            String str = "";
            int cnt = 1;    // 반복되는 값

            // s1와 비교할 문자열
            for (int j = i; j < s.length(); j += i) {
                // 남은 문자열의 길이가 단위(i)보다 작다면
                if (s.length() - j < i) {
                    s2 = s.substring(j);
                } else {
                    s2 = s.substring(j, j + i);
                }

                // 문자열이 반복된다면
                if (s2.equals(s1)) {
                    ++cnt;
                } else {
                    // cnt가 1보다 크다면 앞에 반복된 값을 붙임
                    if (cnt > 1) str += cnt + "";
                    str += s1;
                    // 다음 압축의 기준이 될 문자열 갱신
                    s1 = s2;
                    cnt = 1;
                }
            }

            // 남아있는 문자열
            if (cnt > 1) str += cnt + "";
            str += s1;

            answer = Math.min(answer, str.length());

        }

        return answer;
    }
}