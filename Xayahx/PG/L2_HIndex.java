class HIndex {   
    int[] numbers = new int[10001];
    
    public int solution(int[] citations) {
        setNumbers(citations);
        return findAnswer(citations.length);
    }
    
    public void setNumbers(int[] citations) {
        for(int number : citations) {
            numbers[number]++;
        }    
    }
    
    public int findAnswer(int n) {
        int up = 0;
        
        for(int h=10000; h>=0; h--) {
            up += numbers[h];
            if(isAvailable(n-up, h, up)) {
                return h;
            }
        }
        
        return 0;
    }
    
    public boolean isAvailable(int down, int h, int up) {
        return (h <= up);
    }
}