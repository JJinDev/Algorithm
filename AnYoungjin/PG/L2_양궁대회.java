import java.util.*;

class Solution {
    private int n, diff;
    private int[] info, answer;

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        diff = 0;
        answer = new int[11];

        shoot(0, 0, 0, 10, new int[11]);

        if (Arrays.stream(answer).max().getAsInt() == 0) {
            return new int[] {-1};
        }
        return answer;
    }

    public void shoot(int apeachScore, int ryanScore, int cnt, int idx, int[] ryanInfo) {
        // 이만큼(cnt)의 화살을 쏠 수 없었다면 취소
        if (cnt > n) return;

        // 마지막 과녁
        if (idx < 0) {
            int tempDiff = ryanScore - apeachScore;
            // 라이언이 이긴 경우
            if (tempDiff > diff) {
                if (cnt < n) {
                    // 꼭 n발을 다 쏴야 함
                    // 가장 낮은 점수를 더 많이 맞힌 경우를 return 해야 하므로
                    // 0점에 모두 쏜다
                    ryanInfo[10] = n - cnt;
                }
                answer = Arrays.copyOf(ryanInfo, 11);
                diff = tempDiff;
            }
            return;
        }


        // info[idx] + 1 만큼 쏴보고
        ryanInfo[idx] = info[idx] + 1;
        shoot(apeachScore, ryanScore + 10 - idx, cnt + info[idx] + 1, idx - 1, ryanInfo);

        // 안 쏴보고...
        ryanInfo[idx] = 0;
        if (info[idx] > 0) {
            shoot(apeachScore + 10 - idx, ryanScore, cnt, idx - 1, ryanInfo);

        } else {
            shoot(apeachScore, ryanScore, cnt, idx - 1, ryanInfo);

        }
    }
}