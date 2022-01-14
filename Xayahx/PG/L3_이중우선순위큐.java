import java.util.*;

class L3_이중우선순위큐 {
    // TreeMap의 firstKey, lastKey를 활용
    TreeMap<Integer, Integer> map = new TreeMap<>();
    
    public int[] solution(String[] operations) {
        solve(operations);
        return getAnswer();
    }
    
    public int[] getAnswer() {
        if(map.size() == 0) {
            return new int[]{0,0};
        } else {
            int min = map.firstKey();
            int max = map.lastKey();
            return new int[]{max,min};
        }
    }
    
    public void solve(String[] operations) {
        for(String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            char op = st.nextToken().charAt(0);
            if(op == 'D') {
                if(st.nextToken().length() == 2) {
                    removeNumber(false);
                } else {
                    removeNumber(true);
                }
            } else {
                addNumber(Integer.parseInt(st.nextToken()));
            }
        }
    }
    
    public void addNumber(int number) {
        if(map.containsKey(number)) {
            map.put(number, map.get(number)+1);
        } else {
            map.put(number, 1);
        } 
    }
    
    public boolean isEmpty() {
        return map.size() == 0;
    }
    
    public int getKey(boolean isMax) {
        if(isMax) {
            return map.lastKey();
        } else {
            return map.firstKey();
        }
    }
    
    public void removeNumber(boolean isMax) {
        if(!isEmpty()) {
            int key = getKey(isMax);
            int count = map.get(key);
            if(count == 1) {
                map.remove(key);
            } else {
                map.put(key, count-1);
            }
        }
    }
}