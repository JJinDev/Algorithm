import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        HashMap<String, Integer> a = getMultiSet(str1.toLowerCase());
        HashMap<String, Integer> b = getMultiSet(str2.toLowerCase());
        
        return jaccard(a, b);
    }
    
    public HashMap<String, Integer> getMultiSet(String str) {
        HashMap<String, Integer> multiSet = new HashMap<>();
        
        // 영문자로 된 글자 쌍만 유효
        // 공백, 숫자, 특수 문자가 들어있는 경우 제외
        for (int i = 2; i <= str.length(); i++) {
            String element = str.substring(i - 2, i);
            if (element.charAt(0) < 'a' || element.charAt(0) > 'z'
               || element.charAt(1) < 'a' || element.charAt(1) > 'z') {
                continue;
            }
            multiSet.put(element, multiSet.getOrDefault(element, 0) + 1);
        }
        
        return multiSet;
    }
    
    public int jaccard(HashMap<String, Integer> a, HashMap<String, Integer> b) {
        // 두 집합이 모두 공집합이라면 1
        if (a.size() == 0 && b.size() == 0) {
            return (int) Math.floor(65536 * 1);
        }
        
        int intersectionSize = 0;
        int unionSize = 0;
        
        for (String element : a.keySet()) {
            if (b.containsKey(element)) {
                intersectionSize += Math.min(a.get(element), b.get(element));
                unionSize += Math.max(a.get(element), b.get(element));
                
            } else {
                unionSize += a.get(element);
            }
        }
        
        for (String element : b.keySet()) {
            if (a.containsKey(element)) continue;
            unionSize += b.get(element);
        }
        
        double answer = (double) intersectionSize / (double) unionSize;
        return (int) Math.floor(answer * 65536);
    }
}