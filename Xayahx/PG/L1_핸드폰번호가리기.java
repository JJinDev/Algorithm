class L1_핸드폰번호가리기 {
    public String solution(String phone_number) {
        char[] newNumber = phone_number.toCharArray();
        
        for (int i = 0; i < newNumber.length - 4; i++) {
            newNumber[i] = '*';
        }
        
        String answer = new String(newNumber);
        return answer;
    }
}