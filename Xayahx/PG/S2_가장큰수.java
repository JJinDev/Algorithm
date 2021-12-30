import java.util.*;

class S2_가장큰수 {
    ArrayList<String> strNumbers = new ArrayList<>();
    
    public String solution(int[] numbers) {
        convertNumbersToArrayList(numbers);
        return makeAnswer();
    }
    
    public void convertNumbersToArrayList(int[] numbers) {
        for(int number : numbers) {
            strNumbers.add(Integer.toString(number));
        }
        
        // 내림차순 정렬
        Collections.sort(strNumbers, (a,b) -> (b+a).compareTo(a+b));
    }
    
    public String makeAnswer() {
        // numbers에 모두 0만 들어있을 경우
        if(strNumbers.get(0).startsWith("0")) return "0";
        
        StringBuilder answer = new StringBuilder();
        for(String number : strNumbers) {
            answer.append(number);
        }
        return answer.toString();
    }
}