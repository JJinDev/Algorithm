import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCnt = 0;
        int answerCnt = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCnt++;

            } else if (Arrays.stream(win_nums).anyMatch(x -> x == lotto)) {
                answerCnt++;

            }
        }

        int max = answerCnt + zeroCnt;
        int min = answerCnt;
        return new int[] {Math.min(7 - max, 6), Math.min(7 - min, 6)};
    }
}