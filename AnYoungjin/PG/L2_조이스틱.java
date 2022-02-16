class Solution {
    public int solution(String name) {
        int upDownCnt = 0;
        
        // 초기값: 우측으로만 이동하는 경우
        int leftRightCnt = name.length() - 1;
        
        for (int i = 0; i < name.length(); i++) {
            // 알파벳 변경
            char target = name.charAt(i);
            upDownCnt += Math.min('Z' - target + 1, target - 'A');
            
            // 위치 이동
            int idx = i + 1;
            while (idx < name.length() && name.charAt(idx) == 'A') {
                idx++;
            }
            
            // 우측으로 가다가 좌측으로 이동하는 경우
            //      첫 번째 위치로 돌아가는 조작 횟수 => i * 2
            //      A가 아닌 위치까지 => name.length() - idx
            leftRightCnt = Math.min(leftRightCnt, (i * 2) + name.length() - idx);
            
            // 좌측으로 가다가 우측으로 이동한 경우
            //      좌측으로 이동한 후 첫 번째 위치로 돌아오는 조작 횟수
            //      => (name.length() - idx) * 2
            //      현재 위치까지 => i
            leftRightCnt = Math.min(leftRightCnt, (name.length() - idx) * 2 + i);
        }
        
        return upDownCnt + leftRightCnt;
    }
}