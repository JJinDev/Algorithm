import java.util.*;

class Solution {
    private String hand;
    private int left = 10, right = 11;
    private int[][] numPos = {{3, 1},   // 0
            {0, 0}, {0, 1}, {0, 2},     // 1, 2, 3
            {1, 0}, {1, 1}, {1, 2},     // 4, 5, 6
            {2, 0}, {2, 1}, {2, 2},     // 7, 8, 9
            {3, 0}, {3, 2}};            // *(10), #(11)

    // 숫자 a와 b사이의 거리
    public int getDistance(int a, int b) {
        return Math.abs(numPos[a][0] - numPos[b][0])
                + Math.abs(numPos[a][1] - numPos[b][1]);
    }

    public String pushNumber(int num) {
        if (num == 1 || num == 4 || num == 7) return "L";
        if (num == 3 || num == 6 || num == 9) return "R";

        // 2, 5, 8, 0은 가까운 엄지손가락 사용
        int leftDir = getDistance(this.left, num);
        int rightDir = getDistance(this.right, num);

        if (leftDir < rightDir) return "L";
        if (leftDir > rightDir) return "R";

        // 거리가 같다면 오른손잡이는 오른손, 왼손잡이는 왼손 사용
        return hand;
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        this.hand = hand.equals("right") ? "R" : "L";

        for (int i = 0; i < numbers.length; i++) {
            String thumb = pushNumber(numbers[i]);
            answer.append(thumb);

            // 누른 번호로 손가락 이동
            if (thumb.equals("L")) left = numbers[i];
            else right = numbers[i];
        }

        return answer.toString();
    }
}